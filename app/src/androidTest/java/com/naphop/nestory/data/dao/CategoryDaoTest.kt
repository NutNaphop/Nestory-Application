package com.naphop.nestory.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.naphop.nestory.data.local.NestoryDatabase
import com.naphop.nestory.data.local.dao.CategoryDao
import com.naphop.nestory.data.local.entity.CategoryEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest{
    private lateinit var database: NestoryDatabase
    private lateinit var categoryDao: CategoryDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NestoryDatabase::class.java
        ).build()
        categoryDao = database.categoryDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertCategory_andGetAll_returnsInsertedCategory() = runTest {
        val category = CategoryEntity(
            syncId = "uuid-1",
            name = "Garden"
        )

        categoryDao.insertCategoryItem(category)

        val result = categoryDao.getAllCategoryItems().first()
        assertEquals(category.name,result.first().name)
    }

    @Test
    fun deleteCategory_removeCategoryFromDatabase() = runTest {
        val category = CategoryEntity(
            syncId = "uuid-1",
            name = "Garden"
        )

        categoryDao.insertCategoryItem(category)

        val inserted = categoryDao.getAllCategoryItems().first().first()
        categoryDao.deleteCategoryItem(inserted)

        val result = categoryDao.getAllCategoryItems().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun updateCategory_updatesCategoryInDatabase() = runTest {
        val category = CategoryEntity(
            syncId = "uuid-1",
            name = "Garden"
        )

        categoryDao.insertCategoryItem(category)
        val inserted = categoryDao.getAllCategoryItems().first().first()
        categoryDao.updateCategoryItem(inserted.copy(name = "Baby"))

        val result = categoryDao.getAllCategoryItems().first()

        assertEquals("Baby",result.first().name)
    }

    @Test
    fun getCategoryById_returnsCorrectCategory() = runTest {
        val category = CategoryEntity(
            syncId = "uuid-1",
            name = "Garden"
        )

        categoryDao.insertCategoryItem(category)

        val result = categoryDao.getCategoryItemById(1)

        assertEquals(1,result?.id)
    }
}