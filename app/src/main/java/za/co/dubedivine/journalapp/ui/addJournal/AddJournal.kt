package za.co.dubedivine.journalapp.ui.addJournal

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import za.co.dubedivine.journalapp.R

class AddJournal : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun getStartIntent(context: Context): Intent {
            return Intent(context, AddJournal::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_journal_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AddJournalFragment.newInstance())
                    .commitNow()
        }
    }


}
