package za.co.dubedivine.journalapp.firebaseSyncService

import android.app.job.JobParameters
import android.app.job.JobService
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import za.co.dubedivine.journalapp.executors.AppExecutors
import com.google.firebase.firestore.FirebaseFirestore
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry


class FirebaseSyncService : JobService() {


    private val exec = AppExecutors.networkIO()
    private var journals: LiveData<List<JournalEntry>>? = null

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }


    override fun onStartJob(params: JobParameters?): Boolean {
        exec.execute {

            val database = AppDatabase.getInstance(this.application)
            journals = database.journalDao().loadAllTasks()

            journals?.observeForever {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection("Journals")
                it?.forEach {
                    collection.add(it)
                }
            }
        }
        return true
    }


}