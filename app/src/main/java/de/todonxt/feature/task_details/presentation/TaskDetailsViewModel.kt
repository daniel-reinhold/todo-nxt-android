package de.todonxt.feature.task_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.TaskRepository
import de.todonxt.core.data.source.local.entities.TaskEntity
import de.todonxt.feature.task_details.data.DateState
import de.todonxt.feature.task_details.data.DescriptionState
import de.todonxt.feature.task_details.data.TimeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val descriptionState = MutableStateFlow<DescriptionState?>(null)
    val dateState = MutableStateFlow<DateState?>(null)
    val timeState = MutableStateFlow<TimeState?>(null)

    val title = MutableStateFlow("")
    val descriptionTextFieldText = MutableStateFlow("")

    private val taskID = MutableStateFlow<Int?>(null)
    private val task = MutableStateFlow<TaskEntity?>(null)

    internal sealed class UpdateType {
        data class Description(val updatedDescription: String) : UpdateType()
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
                    descriptionState.value = null
                    dateState.value = null
                    timeState.value = null

                    it?.let { taskEntity ->
                        title.value = taskEntity.title

                        val description = taskEntity.description
                        val date = taskEntity.date
                        val time = taskEntity.time

                        descriptionState.value = if (description != null) {
                            descriptionTextFieldText.value = description
                            DescriptionState.Set(description)
                        } else {
                            DescriptionState.Empty
                        }

                        dateState.value = if (date != null) {
                            DateState.Set(date)
                        } else {
                            DateState.Empty
                        }

                        timeState.value = if (time != null) {
                            TimeState.Set(time)
                        } else {
                            TimeState.Empty
                        }
                    }
                }
            }
        }
    }

    fun getDate(): Calendar? {
        return task.value?.date
    }

    fun getTime(): Calendar? {
        return task.value?.time
    }

    fun startDescriptionEditMode() {
        descriptionState.value = DescriptionState.Edit
    }

    fun updateDescription(description: String) {
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
                    is UpdateType.Description -> {
                        taskRepository.updateTaskDescription(taskEntity, updateType.updatedDescription)
                    }
                    is UpdateType.Date -> {
                        taskRepository.updateTaskDate(taskEntity, updateType.updatedDate)
                    }
                    is UpdateType.Time -> {
                        taskRepository.updateTaskTime(taskEntity, updateType.updatedTime)
                    }
                }.let { entityID ->
                    task.value = null
                    taskRepository.findTask(entityID.toInt()).collectLatest { taskEntity ->
                        task.value = taskEntity
                    }
                }
            }
        }
    }

}