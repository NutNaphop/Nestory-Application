package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category"
)

data class CategoryEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "sync_id")
    val syncId: String,

    @ColumnInfo(name = "name")
    val name: String
)