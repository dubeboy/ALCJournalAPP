package za.co.dubedivine.journalapp.ui.addJournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalDao
import za.co.dubedivine.journalapp.database.JournalEntry

class AddJournalViewModel(private val database: AppDatabase) : ViewModel() {
    private val journalDao: JournalDao = database.journalDao()

    fun insertJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }

    fun updateJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }
}