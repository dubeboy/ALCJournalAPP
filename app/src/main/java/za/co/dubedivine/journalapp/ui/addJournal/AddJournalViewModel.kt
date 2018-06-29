package za.co.dubedivine.journalapp.ui.addJournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalDao
import za.co.dubedivine.journalapp.database.JournalEntry


// these classes survive lifecycles of views
class AddJournalViewModel(database: AppDatabase) : ViewModel() {

    private val journalDao: JournalDao = database.journalDao()
    private var journal: LiveData<JournalEntry>? = null

    private var _id: Int = -1
    var id: Int
        get() = _id
        set(value) {
            _id = value
        }

    fun isInEditMode() = id != -1

    fun getJournalToEdit(): LiveData<JournalEntry>? {
        if (isInEditMode()) {
            if (journal == null) {
                journal = journalDao.findJournalEntry(id)
            }
            return journal
        }
        return null
    }
}