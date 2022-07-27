package de.todonxt.feature.task_list.presentation

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
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val openTasks = taskRepository.getOpenTasks()
    val doneTasks = taskRepository.getDoneTasks()

    val anyOpenTasks = MutableStateFlow(false)
    val anyDoneTasks = MutableStateFlow(false)

    val containerDoneTasksVisible = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            launch {
                openTasks.collectLatest {
                    anyOpenTasks.value = it.isNotEmpty()
                }
            }

            launch {
                doneTasks.collectLatest {
                    anyDoneTasks.value = it.isNotEmpty()
                }
            }
        }
    }

    fun updateDate(task: TaskEntity, date: Calendar?) {
        viewModelScope.launch {
            taskRepository.updateTaskDate(task, date)
        }
    }

    fun updateTime(task: TaskEntity, time: Calendar?) {
        viewModelScope.launch {
            taskRepository.updateTaskTime(task, time)
        }
    }

    fun toggleDoneTasksVisibility() {
        containerDoneTasksVisible.value = !containerDoneTasksVisible.value
    }

}