package de.todonxt.core.data.repository

import de.todonxt.core.data.source.local.dao.TaskDao
import de.todonxt.core.data.source.local.entities.TaskEntity
import java.util.*
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    fun getTasks() = taskDao.getAll()

    suspend fun createTask(
        title: String,
        description: String?,
        time: Calendar?,
        isWholeDay: Boolean
    ) = taskDao.createTask(
        TaskEntity(
            id = null,
            title = title,
            description = description,
            time = time,
            isWholeDay = isWholeDay
        )
    )

    suspend fun updateTask(
        task: TaskEntity,
        title: String,
        description: String?
    ) = task.apply {
        this.title = title
        this.description = description
    }.let {
        taskDao.createTask(it)
    }

    suspend fun updateTaskTime(task: TaskEntity, time: Calendar?) = task.apply {
        this.time = time
    }.let {
        taskDao.createTask(it)
    }

    suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)

}