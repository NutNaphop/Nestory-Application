package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "box"
)
data class BoxEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "sync_id")
    val syncId: String,

    @ColumnInfo(name = "box_name")
    val boxName: String
)