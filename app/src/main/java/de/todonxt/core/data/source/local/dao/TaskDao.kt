package de.todonxt.core.data.source.local.dao

import androidx.room.*
import de.todonxt.core.data.source.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("SELECT *, datetime(tasks.time / 1000, 'unixepoch') AS converted_time FROM tasks WHERE converted_time >= datetime('now', 'start of day') AND converted_time < datetime('now', 'start of day', '+1 day')")
    fun getTasksForToday(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    fun findTask(id: Int): Flow<TaskEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

}