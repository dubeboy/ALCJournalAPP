package za.co.dubedivine.journalapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE


@Dao
interface JournalDao {
    //insert
    // delete
    // edit

    @Query("SELECT * FROM journal ORDER BY modifiedAt DESC")
    fun loadAllTasks(): LiveData<List<JournalEntry>>

    // should be called from a no lifecycle observer eg service
    @Query("SELECT * FROM journal ORDER BY modifiedAt DESC")
    fun loadTasksFromService(): List<JournalEntry>

    @Query("SELECT * FROM journal WHERE id = :id")
    fun findJournalEntry(id: Int): LiveData<JournalEntry>  // find item by ID

    @Insert
    fun insertAll(journals: Array<JournalEntry>)

    @Insert
    fun insert(journal: JournalEntry)

    @Update(onConflict = REPLACE)
    fun update(journal: JournalEntry)



    @Delete
    fun delete(user: JournalEntry)
}