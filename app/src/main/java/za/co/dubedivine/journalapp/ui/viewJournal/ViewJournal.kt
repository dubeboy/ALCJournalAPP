package za.co.dubedivine.journalapp.ui.viewJournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import za.co.dubedivine.journalapp.R

class ViewJournal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_journal_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ViewJournalFragment.newInstance())
                    .commitNow()
        }
    }

}
