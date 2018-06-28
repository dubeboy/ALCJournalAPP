package za.co.dubedivine.journalapp.ui.addJournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalDao
import za.co.dubedivine.journalapp.database.JournalEntry


// these classes survi
class AddJournalViewModel(private val database: AppDatabase) : ViewModel() {
    private val journalDao: JournalDao = database.journalDao()
    private var _id: Int = -1
    var id: Int
        get() = _id
        set(value) {
            _id = value
        }

    fun isInEditMode() = id != -1

    fun getJournalToEdit(): JournalEntry? {
        if (isInEditMode()) {
            return journalDao.findJournalEntry(id)
        }
        return null
    }

    // could keep a ref of the current text being written

    fun insertJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }

    fun updateJournal(journal: JournalEntry) {
        journalDao.insert(journal)
    }

    fun saveJournal(journalEntityToSave: JournalEntry) =
            if (journalEntityToSave.id != null)
                updateJournal(journalEntityToSave)
            else
                insertJournal(journalEntityToSave)
}