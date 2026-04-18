package com.naphop.nestory.domain.model

import java.time.ZoneId
import java.time.Instant

data class Inventory(
    val id: Int,
    val syncId: String,
    val name: String,
    val amount: Int,
    val category: Category?,
    val box: Box?,
    val dueDate: Long?,
)

enum class ExpirationStatus{
    EXPIRED,
    THIS_WEEK,
    THIS_MONTH,
    FRESH
}

fun Inventory.getExpirationStatus(currentTime: Long): ExpirationStatus {
    val date = dueDate ?: return ExpirationStatus.FRESH

    val zoneId = ZoneId.systemDefault()
    val today = Instant.ofEpochMilli(currentTime).atZone(zoneId).toLocalDate()
    val expiryDate = Instant.ofEpochMilli(date).atZone(zoneId).toLocalDate()

    return when {
        expiryDate.isBefore(today) -> ExpirationStatus.EXPIRED
        expiryDate.isBefore(today.plusWeeks(1).plusDays(1)) -> ExpirationStatus.THIS_WEEK
        expiryDate.isBefore(today.plusMonths(1).plusDays(1)) -> ExpirationStatus.THIS_MONTH
        else -> ExpirationStatus.FRESH
    }
}