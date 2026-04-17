package com.naphop.nestory.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naphop.nestory.domain.model.Category
import com.naphop.nestory.domain.model.ExpirationStatus
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.model.getExpirationStatus
import com.naphop.nestory.domain.repository.CategoryRepository
import com.naphop.nestory.domain.repository.InventoryRepository
import com.naphop.nestory.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class ExpirationUiState(
    val selectedCategory: String = "All",
    val categoryList: List<String> = emptyList(),
    val expiredItems: List<Inventory> = emptyList(),
    val expiringThisWeek: List<Inventory> = emptyList(),
    val expiringThisMonth: List<Inventory> = emptyList(),
    val isLoading: Boolean = false
)

class ExpirationViewModel(
    private val repository: InventoryRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow("All")
    private val _inventoryItems = repository.getAllInventories()
    private val _categoryItems = categoryRepository.getAllCategories()

    val uiState: StateFlow<UiState<ExpirationUiState>> = combine(
        repository.getAllInventories(),
        _selectedCategory,
        categoryRepository.getAllCategories()
    ) { items, selectedCategory, categoryItems ->
        val currentTime = System.currentTimeMillis()

        val filteredItems = if (selectedCategory == "All") {
            items
        } else {
            items.filter { it.category?.name == selectedCategory }
        }

        val displayCategory = listOf<String>("All") + categoryItems.map { it.name }
        UiState.Success(
            ExpirationUiState(
                selectedCategory = selectedCategory,
                displayCategory,
                expiredItems = filteredItems.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.EXPIRED },
                expiringThisWeek = filteredItems.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.THIS_WEEK },
                expiringThisMonth = filteredItems.filter { it.getExpirationStatus(currentTime) == ExpirationStatus.THIS_MONTH },
                isLoading = false
            )
        ) as UiState<ExpirationUiState>
    }
        .catch {
            UiState.Error(it.message)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }
}
