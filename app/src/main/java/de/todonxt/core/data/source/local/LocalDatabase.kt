package de.todonxt.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.todonxt.core.data.source.local.dao.TaskDao
import de.todonxt.core.data.source.local.entities.TaskEntity
import de.todonxt.core.data.source.local.typeconverters.LongCalendarConverter

@Database(
    version = 1,
    entities = [TaskEntity::class]
)
@TypeConverters(LongCalendarConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao
}