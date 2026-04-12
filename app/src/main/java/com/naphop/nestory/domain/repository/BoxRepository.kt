package com.naphop.nestory.domain.repository

import com.naphop.nestory.domain.model.Box
import kotlinx.coroutines.flow.Flow

interface BoxRepository {
    fun getAllBoxes(): Flow<List<Box>>
    suspend fun getBoxById(boxId: Int): Box?
    suspend fun insertBox(box: Box)
    suspend fun updateBox(box: Box)
    suspend fun deleteBox(box: Box)
}