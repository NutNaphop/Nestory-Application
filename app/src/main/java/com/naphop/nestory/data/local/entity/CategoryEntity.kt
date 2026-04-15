package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naphop.nestory.domain.model.Category

@Entity(
    tableName = "category"
)

data class CategoryEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "sync_id")
    val syncId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "icon")
    val icon: String
)

fun CategoryEntity.toDomain() = Category(
    id = id,
    syncId = syncId,
    name = name,
    icon = icon
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    syncId = syncId,
    name = name,
    icon = icon
)
