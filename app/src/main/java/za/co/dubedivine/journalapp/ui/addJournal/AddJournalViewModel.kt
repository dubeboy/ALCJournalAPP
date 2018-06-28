package za.co.dubedivine.journalapp.ui.addJournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalDao
import za.co.dubedivine.journalapp.database.JournalEntry

class AddJournalViewModel(application: Application) : AndroidViewModel(application) {
    private val journalDao: JournalDao

    init {
        val database = AppDatabase.getInstance(this.getApplication())
        journalDao = database.journalDao()
    }

    fun insertJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }

    fun updateJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }


}