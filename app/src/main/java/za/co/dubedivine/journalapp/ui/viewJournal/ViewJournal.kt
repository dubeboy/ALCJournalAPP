package za.co.dubedivine.journalapp.ui.viewJournal

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import za.co.dubedivine.journalapp.R

class ViewJournal : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, ViewJournal::class.java)
            intent.putExtra(ViewJournalFragment.EXTRA_JOURNAL_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_journal_activity)
        if (savedInstanceState == null) {
            val id = intent.getIntExtra(ViewJournalFragment.EXTRA_JOURNAL_ID, -1)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ViewJournalFragment.newInstance(id))
                    .commitNow()
        }


    }

}
