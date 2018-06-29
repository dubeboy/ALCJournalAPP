package za.co.dubedivine.journalapp.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry

open class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var journals: LiveData<List<JournalEntry>>? = null

    init {
        val database = AppDatabase.getInstance(getApplication())
         journals = database.journalDao().loadAllTasks()
    }

    fun getAllJournals() = journals

}
