package com.naphop.nestory.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.naphop.nestory.data.local.NestoryDatabase
import com.naphop.nestory.data.local.dao.BoxDao
import com.naphop.nestory.data.local.dao.CategoryDao
import com.naphop.nestory.data.local.dao.InventoryDao
import com.naphop.nestory.data.local.entity.BoxEntity
import com.naphop.nestory.data.local.entity.CategoryEntity
import com.naphop.nestory.data.local.entity.InventoryEntity
import com.naphop.nestory.data.local.entity.InventoryWithDetails
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InventoryDaoTest {

    private lateinit var database: NestoryDatabase
    private lateinit var inventoryDao: InventoryDao
    private lateinit var boxDao: BoxDao
    private lateinit var categoryDao: CategoryDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NestoryDatabase::class.java
        ).build()

        inventoryDao = database.inventoryDao()
        boxDao = database.boxDao()
        categoryDao = database.categoryDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    private suspend fun insertCategory(): CategoryEntity {
        val category = CategoryEntity(syncId = "cat-uuid-1", name = "Food")
        categoryDao.insertCategoryItem(category)
        return categoryDao.getAllCategoryItems().first().first()
    }

    private suspend fun insertBox(): BoxEntity {
        val box = BoxEntity(syncId = "box-uuid-1", boxName = "Kitchen")
        boxDao.insertBoxItem(box)
        return boxDao.getAllBoxItems().first().first()
    }

    private suspend fun insertInventory(): InventoryWithDetails {
        val category = insertCategory()
        val box = insertBox()
        val item = InventoryEntity(
            syncId = "inv-uuid-1",
            name = "Milk",
            amount = 2,
            category = category.id,
            boxGroup = box.id,
            dueDate = null
        )
        inventoryDao.insertInventoryItem(item)
        return inventoryDao.getAllInventoryItems().first().first()
    }

    @Test
    fun insertInventory_andGetAll_ReturnInsertInventory() = runTest{
        val inserted = insertInventory()
        val result = inventoryDao.getAllInventoryItems().first().first().inventory
        assertEquals(inserted.inventory.name,result.name)
    }

    @Test
    fun deleteInventory_removeItemFromDatabase() = runTest {
        val inserted = insertInventory()
        inventoryDao.deleteInventoryItem(inserted.inventory)
        val result = inventoryDao.getAllInventoryItems().first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun updateInventory_updateInventoryInDatabase() = runTest {
        val inserted = insertInventory()
        inventoryDao.updateInventoryItem(
            inserted.inventory.copy(name = "Potato Soup")
        )
        val result = inventoryDao.getAllInventoryItems().first().first()
        assertEquals("Potato Soup", result.inventory.name)
    }
}