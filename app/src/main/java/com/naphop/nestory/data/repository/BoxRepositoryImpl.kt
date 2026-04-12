package com.naphop.nestory.data.repository

import com.naphop.nestory.data.local.dao.BoxDao
import com.naphop.nestory.data.local.entity.toDomain
import com.naphop.nestory.data.local.entity.toEntity
import com.naphop.nestory.domain.model.Box
import com.naphop.nestory.domain.repository.BoxRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BoxRepositoryImpl(
    private val boxDao: BoxDao
) : BoxRepository {
    override fun getAllBoxes(): Flow<List<Box>> {
        return boxDao.getAllBoxItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getBoxById(boxId: Int): Box? {
        return boxDao.getBoxItemById(boxId)?.toDomain()
    }

    override suspend fun insertBox(box: Box) {
        boxDao.insertBoxItem(box.toEntity())
    }

    override suspend fun updateBox(box: Box) {
        boxDao.updateBoxItem(box.toEntity())
    }

    override suspend fun deleteBox(box: Box) {
        boxDao.deleteBoxItem(box.toEntity())
    }
}