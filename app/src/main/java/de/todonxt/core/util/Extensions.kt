package de.todonxt.core.util

fun Int.asUnicode(): String {
    return String(Character.toChars(this))
}