package za.co.dubedivine.journalapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface JournalDao {
    //isert
    // delete
    // edit

    @Query("SELECT * FROM journal ORDER BY modifiedAt DESC")
    fun loadAllTasks(): LiveData<List<JournalEntry>>

    @Query("SELECT * FROM journal WHERE id = :id")
    fun findJournalEntry(id: Int): JournalEntry  // find item by ID

    @Insert
    fun insertAll(journals: Array<JournalEntry>)

    @Insert
    fun insert(journal: JournalEntry)

    @Update
    fun update(journal: JournalEntry)



    @Delete
    fun delete(user: JournalEntry)
}