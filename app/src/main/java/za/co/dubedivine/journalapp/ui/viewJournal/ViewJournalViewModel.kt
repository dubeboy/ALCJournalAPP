package za.co.dubedivine.journalapp.ui.viewJournal

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry

// todo look in to the factory
class ViewJournalViewModel(application: Application) : ViewModel() {
    // todo should cache all the data here bro

    private val journalDao = AppDatabase.getInstance(application.applicationContext).journalDao()

    fun getJournalById(id: Int): LiveData<JournalEntry> {
        return journalDao.findJournalEntry(id)
    }
}
