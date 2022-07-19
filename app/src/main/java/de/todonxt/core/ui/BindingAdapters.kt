package de.todonxt.core.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun bindIsVisible(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, value: Boolean) {
    view.visibility = if (value) View.GONE else View.VISIBLE
}