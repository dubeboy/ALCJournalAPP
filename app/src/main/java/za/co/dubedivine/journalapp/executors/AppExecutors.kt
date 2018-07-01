package za.co.dubedivine.journalapp.executors

import android.util.Log
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

//singleton class
object AppExecutors {

    private val executorService = Executors.newSingleThreadExecutor()!!
    private val networkExecutorService = Executors.newFixedThreadPool(3)

    fun diskIO(): Executor {
        Log.d("AppExecutors", "DiskIO called")
        return executorService
    }

    fun networkIO(): Executor {
        return networkExecutorService
    }
}