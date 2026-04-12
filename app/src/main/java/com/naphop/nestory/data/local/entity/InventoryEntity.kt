package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "inventory",
    foreignKeys = [
        ForeignKey(
            entity = BoxEntity::class,
            parentColumns = ["id"],
            childColumns = ["box_group"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["box_group"]),
        Index(value = ["category"])
    ]
)
data class InventoryEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "sync_id")
    val syncId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "category")
    val category: Int,

    @ColumnInfo(name = "box_group")
    val boxGroup: Int,

    @ColumnInfo(name = "due_date")
    val dueDate: Long?,
)