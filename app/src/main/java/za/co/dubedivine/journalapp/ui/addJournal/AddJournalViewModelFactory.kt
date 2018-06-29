package za.co.dubedivine.journalapp.ui.addJournal

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import za.co.dubedivine.journalapp.database.AppDatabase

@Suppress("UNCHECKED_CAST")
class AddJournalViewModelFactory(private val appDatabase: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddJournalViewModel::class.java)) {
            return AddJournalViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}