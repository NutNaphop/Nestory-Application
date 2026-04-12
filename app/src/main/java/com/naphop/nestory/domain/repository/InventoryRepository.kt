package com.naphop.nestory.domain.repository

import com.naphop.nestory.domain.model.Inventory
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    fun getAllInventories(): Flow<List<Inventory>>
    fun getInventoryInBox(boxId: Int): Flow<List<Inventory>>
    suspend fun getInventoryById(inventoryId: Int): Inventory?
    suspend fun insertInventory(inventory: Inventory)
    suspend fun updateInventory(inventory: Inventory)
    suspend fun deleteInventory(inventory: Inventory)
}