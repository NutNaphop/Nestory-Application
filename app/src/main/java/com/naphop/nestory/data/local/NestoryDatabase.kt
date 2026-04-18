package com.naphop.nestory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.naphop.nestory.data.local.dao.BoxDao
import com.naphop.nestory.data.local.dao.CategoryDao
import com.naphop.nestory.data.local.dao.InventoryDao
import com.naphop.nestory.data.local.entity.BoxEntity
import com.naphop.nestory.data.local.entity.CategoryEntity
import com.naphop.nestory.data.local.entity.InventoryEntity
import com.naphop.nestory.domain.model.SampleData

@Database(
    entities = [BoxEntity::class, CategoryEntity::class, InventoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NestoryDatabase : RoomDatabase() {
    abstract fun boxDao(): BoxDao
    abstract fun categoryDao(): CategoryDao
    abstract fun inventoryDao(): InventoryDao

    companion object {
        suspend fun prepopulateData(db: NestoryDatabase) {
            val categoryDao = db.categoryDao()
            val inventoryDao = db.inventoryDao()

            SampleData.categories.forEach { category ->
                categoryDao.insertCategoryItem(
                    CategoryEntity(
                        id = category.id,
                        syncId = category.syncId,
                        name = category.name,
                        icon = category.icon
                    )
                )
            }

            SampleData.inventoryList.forEach { item ->
                inventoryDao.insertInventoryItem(
                    InventoryEntity(
                        id = item.id,
                        syncId = item.syncId,
                        name = item.name,
                        amount = item.amount,
                        category = item.category?.id ?: 0,
                        boxGroup = item.box?.id,
                        dueDate = item.dueDate
                    )
                )
            }
        }
    }
}
