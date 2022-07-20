package de.todonxt.core.data.source.local.typeconverters

import androidx.room.TypeConverter
import java.util.*

class LongCalendarConverter {

    @TypeConverter
    fun longToCalendar(value: Long?): Calendar? {
        return Calendar.getInstance().apply {
            timeInMillis = value ?: 0
        }
    }

    @TypeConverter
    fun calenderToLong(value: Calendar?): Long? {
        return value?.timeInMillis
    }

}