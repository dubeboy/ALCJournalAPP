package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.IntRange
import android.util.Log
import java.io.Serializable
import java.util.Date

@Entity(tableName = "journal")
data class JournalEntry constructor(@PrimaryKey(autoGenerate = true) var id: Int?,
                                    var title: String,
                                    var body: String,
                                    @IntRange(from = 1, to = 3)  var mood: Int, // 1 neutral 2 happy 3 sad
                                    var modifiedAt: Date,
                                    var createdAt: Date) : Serializable {

    @Ignore
    constructor(title: String,
                body: String,
                mood: Int,
                modifiedAt: Date,
                createdAt: Date) :  this(null, title, body, mood,  modifiedAt,  createdAt)


    override fun toString(): String {
        Log.d("JournalEntry", "the id is $id")
        return super.toString()
    }

    fun isEmpty(): Boolean {
        return title.isBlank() || body.isBlank()
    }
}