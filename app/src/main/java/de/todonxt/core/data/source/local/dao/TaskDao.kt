package de.todonxt.core.data.source.local.dao

import androidx.annotation.WorkerThread
import androidx.room.*
import de.todonxt.core.data.source.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE is_finished = 0")
    fun getOpenTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE is_finished = 1")
    fun getDoneTasks(): Flow<List<TaskEntity>>

    @Query("SELECT *, datetime(tasks.time / 1000, 'unixepoch') AS converted_time FROM tasks WHERE converted_time >= datetime('now', 'start of day') AND converted_time < datetime('now', 'start of day', '+1 day') AND is_finished = 0")
    fun getTasksForToday(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    fun findTask(id: Int): Flow<TaskEntity?>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdateTask(task: TaskEntity): Long

    @WorkerThread
    @Delete
    suspend fun deleteTask(task: TaskEntity): Int

}