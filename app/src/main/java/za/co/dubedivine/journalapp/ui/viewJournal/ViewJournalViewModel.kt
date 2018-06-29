package za.co.dubedivine.journalapp.ui.viewJournal

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry

// todo look in to the factory
class ViewJournalViewModel(database: AppDatabase) : ViewModel() {
    // todo should cache all the data here bro

    private val journalDao = database.journalDao()

    fun getJournalById(id: Int): LiveData<JournalEntry> {
        return journalDao.findJournalEntry(id)
    }
}
