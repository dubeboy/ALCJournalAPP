package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.util.Log
import java.util.Date

@Entity(tableName = "journal")
data class JournalEntry constructor(@PrimaryKey(autoGenerate = true) var id: Int?,
                                    var title: String,
                                    var body: String,
                                    var modifiedAt: Date,
                                    var createdAt: Date) {

    @Ignore
    constructor(title: String,
                body: String,
                modifiedAt: Date,
                createdAt: Date) :  this(null, title, body, modifiedAt,  createdAt)


    override fun toString(): String {
        return super.toString()
        Log.d("JournalEntry", "the id is $id")
    }

    fun isEmpty(): Boolean {
        return title.isBlank() || body.isBlank()
    }
}