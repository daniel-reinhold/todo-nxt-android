package de.todonxt.core.util

import android.content.Context
import androidx.annotation.StringRes
import de.todonxt.R
import java.text.SimpleDateFormat
import java.util.*

private fun formatBase(
    context: Context?,
    calendar: Calendar,
    @StringRes formatResource: Int
): String {
    return context?.let {
        SimpleDateFormat(
            context.getString(formatResource),
            Locale.getDefault()
        ).format(calendar.time)
    } ?: "Formatting error"
}

fun Calendar.formatDateTime(context: Context?): String {
    return formatBase(context, this, R.string.format_date_time)
}

fun Calendar.formatDate(context: Context?): String {
    return formatBase(context, this, R.string.format_date)
}

fun Calendar.formatTime(context: Context?): String {
    return formatBase(context, this, R.string.format_time)
}