package com.example.alfatesttask.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toCardFormat(): String {
    return this.chunked(4).joinToString(" ")
}


fun String.toInputString(): String {
    return this.replace(" ", "")
}

fun calculateNewCursorPosition(
    oldText: String,
    newText: String,
    oldCursorPos: Int
): Int {
    if (oldCursorPos >= oldText.length) {
        return newText.length
    }

    val digitsBeforeCursor = oldText.take(oldCursorPos).count { it.isDigit() }

    var newCursorPos = 0
    var digitsPassed = 0

    for (i in newText.indices) {
        if (digitsPassed >= digitsBeforeCursor) break
        if (newText[i].isDigit()) {
            digitsPassed++
        }
        newCursorPos++
    }

    return newCursorPos
}

fun LocalDateTime.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    return this.format(formatter)
}