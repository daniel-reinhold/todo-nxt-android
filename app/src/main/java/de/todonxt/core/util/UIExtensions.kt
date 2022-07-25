package de.todonxt.core.util

import android.text.format.DateFormat
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

fun Fragment.datePicker(
    @StringRes titleResource: Int? = null,
    selection: Calendar?,
    onDateSet: (date: Calendar) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker().apply {
        titleResource?.let {
            setTitleText(it)
        }
        setSelection(selection?.timeInMillis ?: Calendar.getInstance().timeInMillis)
    }.build()

    datePicker.addOnPositiveButtonClickListener {
        onDateSet.invoke(
            Calendar.getInstance().apply {
                timeInMillis = it
            }
        )
    }

    datePicker.show(childFragmentManager, "DatePickerDialog")
}

fun Fragment.timePicker(
    @StringRes titleResource: Int? = null,
    selection: Calendar?,
    onTimeSet: (date: Calendar) -> Unit
) {
    val now = Calendar.getInstance()
    val timePicker = MaterialTimePicker.Builder().apply {
        titleResource?.let {
            setTitleText(it)
        }
        setTimeFormat(if (DateFormat.is24HourFormat(context)) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H)
        setHour((selection ?: now).get(Calendar.HOUR_OF_DAY))
        setMinute((selection ?: now).get(Calendar.MINUTE))
    }.build()

    timePicker.addOnPositiveButtonClickListener {
        onTimeSet.invoke(
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, timePicker.hour)
                set(Calendar.MINUTE, timePicker.minute)
            }
        )
    }

    timePicker.show(childFragmentManager, "TimePickerDialog")
}

fun View.setOnClickListener(listener: () -> Unit) {
    this.setOnClickListener {
        listener.invoke()
    }
}