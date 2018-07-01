package za.co.dubedivine.journalapp.firebaseSyncService

import android.arch.lifecycle.LiveData
import za.co.dubedivine.journalapp.executors.AppExecutors
import com.google.firebase.firestore.FirebaseFirestore
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry
import java.util.concurrent.Executor


class FirebaseSyncService : com.firebase.jobdispatcher.JobService() {
    private var exec: Executor? = AppExecutors.networkIO()
    private var journals: LiveData<List<JournalEntry>>? = null

    override fun onStopJob(job: com.firebase.jobdispatcher.JobParameters?): Boolean {
        journals = null
        exec = null
        jobFinished(job!!, false)
        return true
    }

    override fun onStartJob(job: com.firebase.jobdispatcher.JobParameters?): Boolean {
        if(exec == null) exec = AppExecutors.networkIO()
        exec?.execute {

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
