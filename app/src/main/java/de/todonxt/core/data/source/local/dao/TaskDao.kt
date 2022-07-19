package de.todonxt.core.data.source.local.dao

import androidx.room.*
import de.todonxt.core.data.source.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

}