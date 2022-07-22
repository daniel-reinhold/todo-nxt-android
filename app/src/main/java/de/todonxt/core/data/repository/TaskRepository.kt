package de.todonxt.core.data.repository

import de.todonxt.core.data.source.local.dao.TaskDao
import de.todonxt.core.data.source.local.entities.TaskEntity
import java.util.*
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    fun getTasks() = taskDao.getTasks()

    fun getTasksForToday() = taskDao.getTasksForToday()

    fun findTask(id: Int) = taskDao.findTask(id)

    suspend fun createTask(
        title: String,
        description: String?,
        date: Calendar?,
        time: Calendar?,
    ) = taskDao.createTask(
        TaskEntity(
            id = null,
            title = title,
            description = description,
            date = date,
            time = time
        )
    )

    suspend fun updateTaskTitle(
        task: TaskEntity,
        title: String
    ) = task.apply {
        this.title = title
    }.let {
        taskDao.createTask(it)
    }

    suspend fun updateTaskDescription(
        task: TaskEntity,
        description: String?
    ) = task.apply {
        this.description = description
    }.let {
        taskDao.createTask(it)
    }

    suspend fun updateTaskDate(task: TaskEntity, date: Calendar?) = task.apply {
        this.date = date
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