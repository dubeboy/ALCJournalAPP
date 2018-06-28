package za.co.dubedivine.journalapp.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var journals: LiveData<List<JournalEntry>>

    init {
        val database = AppDatabase.getInstance(this.getApplication())
        journals = database.journalDao().loadAllTasks()
    }

    fun getAllJournals() = journals

}
