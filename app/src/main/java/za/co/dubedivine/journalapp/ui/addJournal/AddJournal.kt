package za.co.dubedivine.journalapp.ui.addJournal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.add_journal_activity.*
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry
import za.co.dubedivine.journalapp.executors.AppExecutors
import za.co.dubedivine.journalapp.ui.viewJournal.ViewJournalFragment
import java.util.*

class AddJournal : AppCompatActivity() {

    companion object {
        const val TAG = "AddJournal"

        @JvmStatic
        fun getStartIntent(context: Context, id: Int = -1): Intent {
            val intent = Intent(context, AddJournal::class.java)
            intent.putExtra(ViewJournalFragment.EXTRA_JOURNAL_ID, id) // these constants  refres to the same thing so y not share code ???
            return intent
        }

    }

    private lateinit var viewModel: AddJournalViewModel
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_journal_activity)

        et_journal_title.requestFocus()

        // use the application context do reduce memory leaks
        database = AppDatabase.getInstance(this.applicationContext)
        //create the factory to inject the database instance on the ViewModel
        val factory = AddJournalViewModelFactory(database)

        viewModel = ViewModelProviders.of(this, factory).get(AddJournalViewModel::class.java)  // I feel like this is the same as just using AndroidViewModel
        viewModel.id = intent.getIntExtra(ViewJournalFragment.EXTRA_JOURNAL_ID, -1)

        if (viewModel.isInEditMode()) {
            viewModel.getJournalToEdit()!!.observe(this, Observer<JournalEntry?> { t ->
                if (t != null) {
                    et_journal_body.setText(t.body)
                    et_journal_title.setText(t.title)
                } else Log.d(TAG, "oops the Joutrnal entry is null")
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_add_journal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_done -> {
                // save and then finish
                saveJournal(createJournalEntityToSave())
                finish()
            }
        }
        return true
    }

    //todo need to save when the activity is paused

    private fun createJournalEntityToSave(): JournalEntry {
        val body = et_journal_body.text.toString()
        val title = et_journal_title.text.toString()
        val mood = getMood() // default mood neutral

        val createdAtDate = Date()
        return if (viewModel.isInEditMode()) {
            JournalEntry(viewModel.id, title, body, mood, createdAtDate, createdAtDate)
        } else {
            JournalEntry(title, body, mood, createdAtDate, createdAtDate)
        }
    }

    private fun getMood(): Int {
        return when {
            radio_happy.isChecked -> {
               2
            }
            radio_sad.isChecked -> {
                3
            }
            else -> { //neutral
                1
            }
        }
    }

    override fun onBackPressed() {
        val createJournalEntityToSave = createJournalEntityToSave()
        if (!createJournalEntityToSave.isEmpty()) {
            saveJournal(createJournalEntityToSave)
        } else {
            Toast.makeText(this, "cannot autosave a blank journal", Toast.LENGTH_LONG).show()
        }
        super.onBackPressed()
    }


    private fun insertJournal(journal: JournalEntry) {
        AppExecutors.diskIO().execute {
            Log.d(TAG, "executing task insert ")
            database.journalDao().insert(journal)
        }
    }

    //todo kind of redundant since we have the onConflict
    private fun updateJournal(journal: JournalEntry) {
        AppExecutors.diskIO().execute {
            Log.d(TAG, "executing task updating ")
            database.journalDao().update(journal)
        }
    }

    private fun saveJournal(journalEntityToSave: JournalEntry) {
        Log.d(TAG, "save journal called ")
        if (!journalEntityToSave.isEmpty()) {
            if (viewModel.isInEditMode()) {
                updateJournal(journalEntityToSave)
            } else {
                insertJournal(journalEntityToSave)
            }
        } else {
            Toast.makeText(this, "cannot save a blank journal", Toast.LENGTH_LONG).show()
        }
    }
}
