package com.naphop.nestory.feature.home.viewmodel

import app.cash.turbine.test
import com.naphop.nestory.MainDispatcherRule
import com.naphop.nestory.domain.model.Category
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.repository.CategoryRepository
import com.naphop.nestory.domain.repository.InventoryRepository
import com.naphop.nestory.feature.home.ExpirationViewModel
import com.naphop.nestory.util.UiState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExpirationViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val inventoryRepository = mockk<InventoryRepository>()
    private val categoryRepository = mockk<CategoryRepository>()

    private fun prepareMockData(): List<Inventory> {
        val currentTime = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000L

        val expiredItem = Inventory(1, "s1", "Milk", 1, null, null, currentTime - (2 * oneDay))
        val thisWeekItem = Inventory(2, "s2", "Bread", 1, null, null, currentTime + (3 * oneDay))
        val thisMonthItem = Inventory(3, "s3", "Egg", 1, null, null, currentTime + (20 * oneDay))
        val freshItem = Inventory(4, "s4", "Soap", 1, null, null, currentTime + (45 * oneDay))

        return listOf(expiredItem, thisWeekItem, thisMonthItem, freshItem)
    }

    @Before
    fun setup() {
        every { inventoryRepository.getAllInventories() } returns flowOf(emptyList())
        every { categoryRepository.getAllCategories() } returns flowOf(emptyList())
    }

    @Test
    fun `when data loaded, data will group correctly`() = runTest {
        val items = prepareMockData()
        every { inventoryRepository.getAllInventories() } returns flowOf(items)

        val viewModel = ExpirationViewModel(inventoryRepository, categoryRepository)

        viewModel.uiState.test {
            val firstItem = awaitItem()
            val state = if (firstItem is UiState.Loading) awaitItem() else firstItem

            assert(state is UiState.Success)
            val data = (state as UiState.Success).data

            Assert.assertEquals(1, data.expiredItems.size)
            Assert.assertEquals(1, data.expiringThisWeek.size)
            Assert.assertEquals(1, data.expiringThisMonth.size)
        }
    }

    @Test
    fun `when selected category as Medicine it should be show only medicine`() = runTest {
        val medicineCategory = Category(id = 1, syncId = "c1", name = "Medicine", icon = "💊")
        val currentTime = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000L

        val testItems = listOf(
            Inventory(5, "s5", "Paracetamol", 1, medicineCategory, null, currentTime - (1 * oneDay)),
            Inventory(1, "s1", "Milk", 1, null, null, currentTime - (1 * oneDay))
        )

        every { inventoryRepository.getAllInventories() } returns flowOf(testItems)
        every { categoryRepository.getAllCategories() } returns flowOf(listOf(medicineCategory))

        val viewModel = ExpirationViewModel(inventoryRepository, categoryRepository)

        viewModel.uiState.test {
            var state = awaitItem()
            if (state is UiState.Loading) state = awaitItem()

            viewModel.setSelectedCategory("Medicine")

            val result = awaitItem() as UiState.Success
            val data = result.data

            Assert.assertEquals(1, data.expiredItems.size)
            Assert.assertEquals("Paracetamol", data.expiredItems[0].name)
        }
    }

    @Test
    fun `when repository throws exception, uiState should emit Error`() = runTest {
        every { inventoryRepository.getAllInventories() } returns flow {
            throw Exception("Database Error")
        }

        val viewModel = ExpirationViewModel(inventoryRepository, categoryRepository)
        viewModel.uiState.test {
            val firstItem = awaitItem()
            val state = if (firstItem is UiState.Loading) awaitItem() else firstItem
            Assert.assertTrue(state is UiState.Error)
            Assert.assertEquals("Database Error", (state as UiState.Error).message)
        }
    }
}
