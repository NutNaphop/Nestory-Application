package com.naphop.nestory.ui.mapper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date(this))
}

fun Long?.toExpiryDateString(): String {
    return this?.toFormattedDate() ?: "No expiration"
}

fun Long.toShortDate(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(Date(this))
}