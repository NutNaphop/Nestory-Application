package com.naphop.nestory.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naphop.nestory.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao{
    @Query("SELECT * FROM category")
    fun getAllCategoryItems() : Flow<List<CategoryEntity>>

    @Query(value = "SELECT * FROM category WHERE id = :categoryId")
    suspend fun getCategoryItemById(categoryId: Int) : CategoryEntity?

    @Insert
    suspend fun insertCategoryItem(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategoryItem(categoryEntity: CategoryEntity)

    @Delete
    suspend fun deleteCategoryItem(categoryEntity: CategoryEntity)
}