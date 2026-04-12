package com.naphop.nestory.data.repository

import com.naphop.nestory.data.local.dao.CategoryDao
import com.naphop.nestory.data.local.entity.toDomain
import com.naphop.nestory.data.local.entity.toEntity
import com.naphop.nestory.domain.model.Category
import com.naphop.nestory.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategoryItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getCategoryById(categoryId: Int): Category? {
        return categoryDao.getCategoryItemById(categoryId)?.toDomain()
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategoryItem(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategoryItem(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategoryItem(category.toEntity())
    }

}