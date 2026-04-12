package com.naphop.nestory.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.naphop.nestory.data.local.entity.InventoryEntity
import com.naphop.nestory.data.local.entity.InventoryWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao{
    @Query("SELECT * FROM inventory")
    fun getAllInventoryItems() : Flow<List<InventoryWithDetails>>

    @Transaction
    @Query("SELECT * FROM inventory WHERE box_group = :boxId")
    fun getInventoryItemsByBoxId(boxId: Int) : Flow<List<InventoryWithDetails>>

    @Query("SELECT * FROM inventory WHERE id = :inventoryId")
    suspend fun getInventoryItemById(inventoryId: Int) : InventoryWithDetails?

    @Insert
    suspend fun insertInventoryItem(inventoryEntity: InventoryEntity)

    @Update
    suspend fun updateInventoryItem(inventoryEntity: InventoryEntity)

    @Delete
    suspend fun deleteInventoryItem(inventoryEntity: InventoryEntity)
}