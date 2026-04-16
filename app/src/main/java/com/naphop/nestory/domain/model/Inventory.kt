package com.naphop.nestory.domain.model

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

    val diff = date - currentTime
    val oneDay = 24 * 60 * 60 * 1000L

    return when {
        diff < 0 -> ExpirationStatus.EXPIRED
        diff <= 7 * oneDay -> ExpirationStatus.THIS_WEEK
        diff <= 30 * oneDay -> ExpirationStatus.THIS_MONTH
        else -> ExpirationStatus.FRESH
    }
}