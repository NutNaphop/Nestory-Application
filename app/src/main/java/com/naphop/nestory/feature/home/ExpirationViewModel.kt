package com.naphop.nestory.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naphop.nestory.domain.model.ExpirationStatus
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.model.getExpirationStatus
import com.naphop.nestory.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ExpirationUiState(
    val expiredItems: List<Inventory> = emptyList(),
    val expiringThisWeek: List<Inventory> = emptyList(),
    val expiringThisMonth: List<Inventory> = emptyList(),
    val isLoading: Boolean = false
)


class ExpirationViewModel(
    private val repository: InventoryRepository
) : ViewModel(){
    val uiState: StateFlow<ExpirationUiState> = repository.getAllInventories().map {
        items ->
        val currentTime = System.currentTimeMillis()

        ExpirationUiState(
            expiredItems = items.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.EXPIRED},
            expiringThisWeek = items.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.THIS_WEEK},
            expiringThisMonth = items.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.THIS_MONTH},
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExpirationUiState(isLoading = true)
    )
}