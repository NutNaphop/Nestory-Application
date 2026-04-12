package com.naphop.nestory.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naphop.nestory.data.local.entity.BoxEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoxDao{
    @Query("SELECT * FROM box")
    fun getAllBoxItems() : Flow<List<BoxEntity>>

    @Query("SELECT * FROM box WHERE id = :boxId")
    suspend fun getBoxItemById(boxId: Int) : BoxEntity?

    @Insert
    suspend fun insertBoxItem(boxEntity: BoxEntity)

    @Update
    suspend fun updateBoxItem(boxEntity: BoxEntity)

    @Delete
    suspend fun deleteBoxItem(boxEntity: BoxEntity)
}