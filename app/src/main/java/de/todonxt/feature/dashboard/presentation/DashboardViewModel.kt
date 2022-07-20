package de.todonxt.feature.dashboard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    taskRepository: TaskRepository
) : ViewModel () {

    val tasks = taskRepository.getTasksForToday()
    val anyTasksForToday = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            launch {
                tasks.collectLatest {
                    Log.i("APP_TEST", "Amount of tasks: ${it.size}")

                    anyTasksForToday.value = it.isNotEmpty()
                }
            }
        }
    }

}