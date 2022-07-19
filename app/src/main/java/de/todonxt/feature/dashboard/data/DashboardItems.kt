package de.todonxt.feature.dashboard.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import de.todonxt.R

data class DashboardItem(
    @StringRes val titleResource: Int,
    @DrawableRes val iconResource: Int,
    val action: Int
)

const val ACTION_SHOW_TASKS = 10
const val ACTION_SHOW_LABELS = 20
const val ACTION_SHOW_PROJECTS = 30

val DASHBOARD_ITEMS = listOf(
    DashboardItem(
        titleResource = R.string.title_tasks,
        iconResource = R.drawable.ic_task,
        action = ACTION_SHOW_TASKS
    ),
    DashboardItem(
        titleResource = R.string.title_labels,
        iconResource = R.drawable.ic_label,
        action = ACTION_SHOW_LABELS
    ),
    DashboardItem(
        titleResource = R.string.title_projects,
        iconResource = R.drawable.ic_project,
        action = ACTION_SHOW_PROJECTS
    )
)