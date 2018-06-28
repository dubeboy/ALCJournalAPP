package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.Dao
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Delete




@Dao
interface JournalDao {
    //isert
    // delete
    // edit

    @Query("SELECT * FROM journal ORDER BY createdAt ASC")
    fun loadAllTasks(): LiveData<List<JournalEntry>>

    @Query("SELECT * FROM journal WHERE id = :id")
    fun findJournalEntry(id: Int): JournalEntry  // find item by ID

    @Insert
    fun insertAll(journals: Array<JournalEntry>)

    @Insert
    fun insert(journals: JournalEntry)

    @Delete
    fun delete(user: JournalEntry)
}