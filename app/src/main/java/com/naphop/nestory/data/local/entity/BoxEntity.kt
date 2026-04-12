package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naphop.nestory.domain.model.Box

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

fun BoxEntity.toDomain() = Box(
    id = id,
    syncId = syncId,
    boxName = boxName
)

fun Box.toEntity() = BoxEntity(
    id = id,
    syncId = syncId,
    boxName = boxName
)