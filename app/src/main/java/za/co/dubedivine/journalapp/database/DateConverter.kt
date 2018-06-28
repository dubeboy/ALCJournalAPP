package za.co.dubedivine.journalapp.database

import android.arch.persistence.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateConverter {

    companion object {
        @JvmStatic
        @TypeConverter
        fun toDate(timestamp: Long?): Date? = if (timestamp == null) {
            null
        } else {
            Date(timestamp)
        }

        @JvmStatic
        @TypeConverter
        fun toTimeStamp(date: Date?): Long? {
            return date?.time
        }
    }
}