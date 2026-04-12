package com.naphop.nestory.data.repository

import com.naphop.nestory.data.local.dao.InventoryDao
import com.naphop.nestory.data.local.entity.toDomain
import com.naphop.nestory.data.local.entity.toEntity
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InventoryRepositoryImpl(
    private val inventoryDao: InventoryDao
) : InventoryRepository {
    override fun getAllInventories(): Flow<List<Inventory>> {
        return inventoryDao.getAllInventoryItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInventoryInBox(boxId: Int): Flow<List<Inventory>> {
        return inventoryDao.getInventoryItemsByBoxId(boxId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getInventoryById(inventoryId: Int): Inventory? {
        return inventoryDao.getInventoryItemById(inventoryId)?.toDomain()
    }

    override suspend fun insertInventory(inventory: Inventory) {
        inventoryDao.insertInventoryItem(inventory.toEntity())
    }

    override suspend fun updateInventory(inventory: Inventory) {
        inventoryDao.updateInventoryItem(inventory.toEntity())
    }

    override suspend fun deleteInventory(inventory: Inventory) {
        inventoryDao.deleteInventoryItem(inventory.toEntity())
    }

}