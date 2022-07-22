package de.todonxt.feature.task_details.data

import java.util.*

sealed class TimeState {
    object Empty : TimeState()
    data class Set(val time: Calendar) : TimeState()
}
