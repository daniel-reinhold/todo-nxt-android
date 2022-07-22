package de.todonxt.feature.task_details.data

import java.util.*

sealed class DateState {
    object Empty : DateState()
    data class Set(val date: Calendar) : DateState()
}
