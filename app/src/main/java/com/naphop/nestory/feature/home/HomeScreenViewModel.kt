package com.naphop.nestory.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naphop.nestory.domain.model.ExpirationStatus
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.model.getExpirationStatus
import com.naphop.nestory.domain.repository.BoxRepository
import com.naphop.nestory.domain.repository.CategoryRepository
import com.naphop.nestory.domain.repository.InventoryRepository
import com.naphop.nestory.util.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class HomeScreenUiState(
    val countItem: Int = 0,
    val countExpiring: Int = 0,
    val countBox: Int = 0,
    val countCategory: Int = 0,
    val expiredItems: List<Inventory> = emptyList(),
)

class HomeScreenViewModel(
    private val inventoryRepository: InventoryRepository,
    private val boxRepository: BoxRepository,
    private val categoryRepository: CategoryRepository
): ViewModel(){

    val uiState: StateFlow<UiState<HomeScreenUiState>> = combine(
        inventoryRepository.getAllInventories(), 
        boxRepository.getAllBoxes(),   
        categoryRepository.getAllCategories() 
    ) { items, boxes, categories ->
        val currentTime = System.currentTimeMillis()
        val expiringSoon = items.filter {
            val status = it.getExpirationStatus(currentTime)
            status == ExpirationStatus.EXPIRED || status == ExpirationStatus.THIS_WEEK
        }

        UiState.Success(
            HomeScreenUiState(
                countItem = items.size,
                countExpiring = expiringSoon.size,
                countBox = boxes.size,
                countCategory = categories.size,
                expiredItems = expiringSoon.take(3)
            )
        ) as UiState<HomeScreenUiState>
    }.catch { e ->
        emit(UiState.Error(e.message))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )
}
