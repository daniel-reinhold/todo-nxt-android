package de.todonxt.feature.task_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getTasks()
    val anyTasks = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            launch {
                tasks.collectLatest {
                    anyTasks.value = it.isNotEmpty()
                }
            }
        }
    }

}