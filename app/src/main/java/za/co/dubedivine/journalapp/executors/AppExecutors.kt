package za.co.dubedivine.journalapp.executors

import java.util.concurrent.Executor
import java.util.concurrent.Executors

//singleton class
object AppExecutors {

    fun diskIO(): Executor = Executors.newSingleThreadExecutor()
}