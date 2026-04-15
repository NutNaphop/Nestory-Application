package com.naphop.nestory.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.naphop.nestory.domain.model.Category
import com.naphop.nestory.domain.model.Inventory

@Entity(
    tableName = "inventory",
    foreignKeys = [
        ForeignKey(
            entity = BoxEntity::class,
            parentColumns = ["id"],
            childColumns = ["box_group"],
            onDelete = ForeignKey.SET_NULL
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
    val boxGroup: Int?,

    @ColumnInfo(name = "due_date")
    val dueDate: Long?,
)

data class InventoryWithDetails(
    @Embedded val inventory: InventoryEntity,

    @Relation(parentColumn = "category", entityColumn = "id")
    val category: CategoryEntity?,

    @Relation(parentColumn = "box_group", entityColumn = "id")
    val box: BoxEntity?
)

fun InventoryWithDetails.toDomain() = Inventory(
    id = inventory.id,
    syncId = inventory.syncId,
    name = inventory.name,
    amount = inventory.amount,
    category = category?.toDomain(),
    box = box?.toDomain(),
    dueDate = inventory.dueDate
)

fun Inventory.toEntity() = InventoryEntity(
    id = id,
    syncId = syncId,
    name = name,
    amount = amount,
    category = category?.id ?: 0,
    boxGroup = box?.id ?: 0,
    dueDate = dueDate
)
