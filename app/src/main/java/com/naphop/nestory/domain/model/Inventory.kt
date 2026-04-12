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