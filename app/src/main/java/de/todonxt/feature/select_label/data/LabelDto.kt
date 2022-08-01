package de.todonxt.feature.select_label.data

import de.todonxt.core.data.source.local.entities.LabelEntity

data class LabelDto(
    val label: LabelEntity,
    var isSelected: Boolean = false
)