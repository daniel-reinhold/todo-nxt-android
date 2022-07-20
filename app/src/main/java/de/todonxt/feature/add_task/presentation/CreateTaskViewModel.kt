package de.todonxt.feature.add_task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val title = MutableStateFlow("")
    val description = MutableStateFlow("")
    val date = MutableStateFlow<Calendar?>(null)
    val time = MutableStateFlow<Calendar?>(null)

    val formValid = MutableStateFlow(false)
    val dateSet = MutableStateFlow(false)
    val timeSet = MutableStateFlow(false)

    val taskCreated = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            launch {
                title.collectLatest {
                    formValid.value = it.isNotBlank()
                }
            }

            launch {
                date.collectLatest {
                    dateSet.value = it != null
                }
            }

            launch {
                time.collectLatest {
                    timeSet.value = it != null
                }
            }
        }
    }

    fun setDate(date: Calendar) {
        this.date.value = date
    }

    fun getDate(): Calendar? {
        return this.date.value
    }

    fun clearDate() {
        this.date.value = null
    }

    fun setTime(time: Calendar) {
        this.time.value = time

        if (this.date.value == null) {
            this.date.value = Calendar.getInstance()
        }
    }

    fun getTime(): Calendar? {
        return time.value
    }

    fun clearTime() {
        this.time.value = null
    }

    fun saveTask() {
        viewModelScope.launch {
            taskRepository.createTask(
                title = title.value,
                description = description.value.ifBlank { null },
                date = date.value,
                time = time.value
            )
        }.invokeOnCompletion {
            taskCreated.value = true
        }
    }

}