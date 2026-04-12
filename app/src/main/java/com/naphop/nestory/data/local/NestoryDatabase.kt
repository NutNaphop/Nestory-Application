package com.naphop.nestory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naphop.nestory.data.local.dao.BoxDao
import com.naphop.nestory.data.local.dao.CategoryDao
import com.naphop.nestory.data.local.dao.InventoryDao
import com.naphop.nestory.data.local.entity.BoxEntity
import com.naphop.nestory.data.local.entity.CategoryEntity
import com.naphop.nestory.data.local.entity.InventoryEntity

@Database(
    entities = [BoxEntity::class, CategoryEntity::class, InventoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NestoryDatabase: RoomDatabase(){
    abstract fun boxDao() : BoxDao
    abstract fun categoryDao(): CategoryDao
    abstract fun inventoryDao(): InventoryDao
}