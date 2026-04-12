package com.naphop.nestory.domain.repository

import com.naphop.nestory.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories() : Flow<List<Category>>
    suspend fun getCategoryById(categoryId: Int) : Category?
    suspend fun insertCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(category: Category)
}