package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity(tableName = "journal")
data class JournalEntry constructor(@PrimaryKey(autoGenerate = true) var id: Int,
                                    var title: String,
                                    var body: String,
                                    var createdAt: Date) {

    @Ignore
    constructor(title: String,
                body: String,
                createdAt: Date) :  this(0, title, body, createdAt)
}