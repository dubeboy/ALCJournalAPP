package za.co.dubedivine.journalapp.ui.addJournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase

class AddJournalViewModel(application: Application) : AndroidViewModel(application) {
    init {
        val database = AppDatabase.getInstance(this.getApplication())

    }
}
