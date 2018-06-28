package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import java.security.AccessControlContext

@Database(entities = [JournalEntry::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun journalDao(): JournalDao

    // could have used the object class but this allows me to fine tune
    companion object {

        private val LOCK: Any = Any()
        private const val DATABASE_NAME = "journalDB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null)
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
                }
            return instance!!
        }
    }
}