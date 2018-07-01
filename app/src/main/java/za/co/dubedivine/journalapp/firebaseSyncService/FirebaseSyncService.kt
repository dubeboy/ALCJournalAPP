package za.co.dubedivine.journalapp.firebaseSyncService

import android.arch.lifecycle.LiveData
import android.support.v7.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import za.co.dubedivine.journalapp.executors.AppExecutors
import com.google.firebase.firestore.FirebaseFirestore
import za.co.dubedivine.journalapp.database.AppDatabase
import za.co.dubedivine.journalapp.database.JournalEntry
import za.co.dubedivine.journalapp.ui.settings.PreferenceSettingFragment.Companion.PREF_EMAIL
import java.util.concurrent.Executor
import java.util.HashMap


class FirebaseSyncService : com.firebase.jobdispatcher.JobService() {
    private var exec: Executor? = AppExecutors.networkIO()
    private var journals: LiveData<List<JournalEntry>>? = null

    override fun onStopJob(job: com.firebase.jobdispatcher.JobParameters?): Boolean {
        journals = null
        exec = null
        Log.d("FirebaseSyncService", "stopping job")

//        jobFinished(job!!, false)
        return true
    }

    override fun onStartJob(job: com.firebase.jobdispatcher.JobParameters?): Boolean {
        if(exec == null) exec = AppExecutors.networkIO()
        Log.d("FirebaseSyncService", "starting job")
        exec?.execute {
            val database = AppDatabase.getInstance(this.application)
            journals = database.journalDao().loadAllTasks()

            val pref =  PreferenceManager.getDefaultSharedPreferences(this)
            val email = pref.getString(PREF_EMAIL, "")
            val map = HashMap<String, Any>()
            journals?.observeForever {
                val db = FirebaseFirestore.getInstance()
                val collection = db.collection("Journals")
                it?.forEach {
                    map.put("title", it.title)
                    map.put("body", it.body)
                    map.put("email", email)
                    map.put("created_at", it.createdAt)
                    map.put("modified_at", it.modifiedAt)
                    map.put("mood", it.mood)
                }

                collection.add(map)
            }
        }
        return true
    }
}
