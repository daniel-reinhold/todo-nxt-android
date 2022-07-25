package de.todonxt.feature.task_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.TaskRepository
import de.todonxt.core.data.source.local.entities.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val title = MutableStateFlow("")
    val description = MutableStateFlow<String?>(null)
    val date = MutableStateFlow<Calendar?>(null)
    val time = MutableStateFlow<Calendar?>(null)

    private val taskID = MutableStateFlow<Int?>(null)
    private val task = MutableStateFlow<TaskEntity?>(null)
    val taskDeleted = MutableStateFlow(false)

    internal sealed class UpdateType {
        data class Title(val updatedTitle: String) : UpdateType()
        data class Description(val updatedDescription: String?) : UpdateType()
        data class Date(val updatedDate: Calendar?) : UpdateType()
        data class Time(val updatedTime: Calendar?) : UpdateType()
    }

    fun setTaskID(id: Int) {
        taskID.value = id
    }

    init {
        // Get initial Task
        viewModelScope.launch {
            launch {
                taskID.collectLatest { id ->
                    id?.let {
                        taskRepository.findTask(it).collectLatest { taskEntity ->
                            task.value = taskEntity
                        }
                    }
                }
            }

            // Update states when task data has changed
            launch {
                task.collectLatest {
                    description.value = null
                    date.value = null
                    time.value = null

                    it?.let { taskEntity ->
                        title.value = taskEntity.title
                        description.value = taskEntity.description
                        date.value = taskEntity.date
                        time.value = taskEntity.time
                    }
                }
            }
        }
    }

    fun getTitleText(): String? {
        return task.value?.title
    }

    fun getDescriptionText(): String? {
        return task.value?.description
    }

    fun getDate(): Calendar? {
        return task.value?.date
    }

    fun getTime(): Calendar? {
        return task.value?.time
    }

    fun updateTitle(title: String) {
        update(
            UpdateType.Title(title)
        )
    }

    fun updateDescription(description: String?) {
        update(
            UpdateType.Description(description)
        )
    }

    fun updateDate(date: Calendar?) {
        update(
            UpdateType.Date(date)
        )
    }

    fun updateTime(time: Calendar?) {
        update(
            UpdateType.Time(time)
        )
    }

    private fun update(updateType: UpdateType) {
        task.value?.let { taskEntity ->
            viewModelScope.launch {
                when (updateType) {
                    is UpdateType.Title ->
                        taskRepository.updateTaskTitle(taskEntity, updateType.updatedTitle)

                    is UpdateType.Description ->
                        taskRepository.updateTaskDescription(taskEntity, updateType.updatedDescription)

                    is UpdateType.Date ->
                        taskRepository.updateTaskDate(taskEntity, updateType.updatedDate)

                    is UpdateType.Time ->
                        taskRepository.updateTaskTime(taskEntity, updateType.updatedTime)
                }.let { entityID ->
                    task.value = null
                    taskRepository.findTask(entityID.toInt()).collectLatest { taskEntity ->
                        task.value = taskEntity
                    }
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            task.value?.let {
                taskRepository.deleteTask(it)
            }
        }.invokeOnCompletion {
            taskDeleted.value = true
        }
    }

}