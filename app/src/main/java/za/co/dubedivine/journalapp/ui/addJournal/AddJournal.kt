package za.co.dubedivine.journalapp.ui.addJournal

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.add_journal_activity.*
import za.co.dubedivine.journalapp.R
import za.co.dubedivine.journalapp.database.JournalEntry
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_journal_activity)
        viewModel = ViewModelProviders.of(this).get(AddJournalViewModel::class.java)
        viewModel.id = intent.getIntExtra(ViewJournalFragment.EXTRA_JOURNAL_ID, -1)

        if (viewModel.isInEditMode()) {
            val (id, title, body, modifiedAt, createdAt) = viewModel.getJournalToEdit()!!
            et_journal_body.setText(body)
            et_journal_title.setText(title)
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
                viewModel.saveJournal(createJournalEntityToSave())
                finish()
            }
        }
        return true
    }

    //todo need to save when the activity is paused

    private fun createJournalEntityToSave(): JournalEntry {
        val body = et_journal_body.text.toString()
        val title = et_journal_title.text.toString()
        val createdAtDate = Date()
        return if (viewModel.isInEditMode()) {
            JournalEntry(viewModel.id, title, body, createdAtDate, createdAtDate)
        } else {
            JournalEntry(title, body, createdAtDate, createdAtDate)
        }
    }

    override fun onBackPressed() {
        viewModel.saveJournal(createJournalEntityToSave())
        super.onBackPressed()
    }
}
