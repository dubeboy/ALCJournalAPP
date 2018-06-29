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

    @Query("SELECT * FROM journal WHERE id = :id")
    fun findJournalEntry(id: Int): JournalEntry  // find item by ID

    @Insert(onConflict = REPLACE)
    fun insertAll(journals: Array<JournalEntry>)

    @Insert(onConflict = REPLACE)
    fun insert(journal: JournalEntry)

    @Update(onConflict = REPLACE)
    fun update(journal: JournalEntry)



    @Delete
    fun delete(user: JournalEntry)
}