package za.co.dubedivine.journalapp.executors

import android.util.Log
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//singleton class
object AppExecutors {

    private val executorService = Executors.newSingleThreadExecutor()!!

    fun diskIO(): Executor {
        Log.d("AppExecutors", "DiskIO called")
        return executorService
    }
}