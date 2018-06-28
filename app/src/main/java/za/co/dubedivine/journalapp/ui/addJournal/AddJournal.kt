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
import java.util.*

class AddJournal : AppCompatActivity() {

    companion object {

        const val TAG = "AddJournal"

        @JvmStatic
        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, AddJournal::class.java)
            return intent
        }

    }

    private lateinit var viewModel: AddJournalViewModel
    private val createdAt: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_journal_activity)
        viewModel = ViewModelProviders.of(this).get(AddJournalViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_add_journal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_done -> {
                // save and then finish
                viewModel.insertJournal(createJournalEntityToSave())
                finish()
            }
        }
       return true
    }

    // need to save when the activity is paused

    private fun createJournalEntityToSave(): JournalEntry {
        val body = et_journal_body.text.toString()
        val title = et_journal_title.text.toString()
        return JournalEntry(title, body, Date(), createdAt)
    }

    override fun onBackPressed() {
        viewModel.insertJournal(createJournalEntityToSave())
        super.onBackPressed()
    }
}
