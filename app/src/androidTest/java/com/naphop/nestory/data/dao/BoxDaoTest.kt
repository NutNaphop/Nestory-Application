package com.naphop.nestory.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.naphop.nestory.data.local.NestoryDatabase
import com.naphop.nestory.data.local.dao.BoxDao
import com.naphop.nestory.data.local.entity.BoxEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoxDaoTest{
    private lateinit var database: NestoryDatabase
    private lateinit var boxDao: BoxDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NestoryDatabase::class.java
        ).build()
        boxDao = database.boxDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertBox_andGetAll_returnsInsertedBox() = runTest {
        val box = BoxEntity(
            syncId = "uuid-1",
            boxName = "Kitchen",
            icon = ""
        )

        boxDao.insertBoxItem(box)

        val result = boxDao.getAllBoxItems().first()
        assertEquals(box.boxName,result.first().boxName)
    }

    @Test
    fun deleteBox_removeBoxFromDatabase() = runTest {
        val box = BoxEntity(
            syncId = "uuid-1",
            boxName = "Kitchen",
            icon = ""
        )

        boxDao.insertBoxItem(box)

        val inserted = boxDao.getAllBoxItems().first().first()
        boxDao.deleteBoxItem(inserted)

        val result = boxDao.getAllBoxItems().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun updateBox_updatesBoxInDatabase() = runTest {
        val box = BoxEntity(
            syncId = "uuid-1",
            boxName = "Kitchen",
            icon = ""
        )

        boxDao.insertBoxItem(box)
        val inserted = boxDao.getAllBoxItems().first().first()
        boxDao.updateBoxItem(inserted.copy(boxName = "Hell Kitchen"))

        val result = boxDao.getAllBoxItems().first()

        assertEquals("Hell Kitchen",result.first().boxName)
    }

    @Test
    fun getBoxById_returnsCorrectBox() = runTest {
        val box = BoxEntity(
            syncId = "uuid-1",
            boxName = "Kitchen",
            icon = ""
        )

        boxDao.insertBoxItem(box)

        val result = boxDao.getBoxItemById(1)

        assertEquals(1,result?.id)
    }
}