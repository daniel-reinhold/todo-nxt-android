package de.todonxt.feature.task_details.data

sealed class DescriptionState {
    object Empty : DescriptionState()
    data class Set(val text: String) : DescriptionState()
    object Edit : DescriptionState()
}
