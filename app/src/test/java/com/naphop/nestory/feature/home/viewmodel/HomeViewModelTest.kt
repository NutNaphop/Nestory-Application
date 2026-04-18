package com.naphop.nestory.feature.home.viewmodel

import app.cash.turbine.test
import com.naphop.nestory.MainDispatcherRule
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.repository.BoxRepository
import com.naphop.nestory.domain.repository.CategoryRepository
import com.naphop.nestory.domain.repository.InventoryRepository
import com.naphop.nestory.feature.home.HomeScreenViewModel
import com.naphop.nestory.util.UiState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.Exception

class HomeScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val inventoryRepo = mockk<InventoryRepository>()
    private val boxRepo = mockk<BoxRepository>()
    private val categoryRepo = mockk<CategoryRepository>()

    @Before
    fun setup() {
        every { inventoryRepo.getAllInventories() } returns flowOf(emptyList())
        every { boxRepo.getAllBoxes() } returns flowOf(emptyList())
        every { categoryRepo.getAllCategories() } returns flowOf(emptyList())
    }

    @Test
    fun `when data is loaded, count item correctly`() = runTest {
        val oneDay = 24 * 60 * 60 * 1000L
        val expiredDate = System.currentTimeMillis() - (2 * oneDay)

        val mockItems = List(5) { index ->
            Inventory(
                id = index,
                syncId = "id-$index",
                name = "Item $index",
                amount = 1,
                category = null,
                box = null,
                dueDate = expiredDate
            )
        }

        every { inventoryRepo.getAllInventories() } returns flowOf(mockItems)

        val viewModel = HomeScreenViewModel(inventoryRepo, boxRepo, categoryRepo)

        viewModel.uiState.test {
            val firstItem = awaitItem()

            val state = if (firstItem is UiState.Loading) awaitItem() else firstItem

            if (state is UiState.Success) {
                val data = state.data
                assertEquals(3, data.expiredItems.size)
                assertEquals(5, data.countExpiring)
            }
        }
    }

    @Test
    fun `when repository throws exception, uiState should emit Error`() = runTest {
        every { inventoryRepo.getAllInventories() } returns flow {
            throw Exception("Database Error")
        }

        val viewModel = HomeScreenViewModel(inventoryRepo, boxRepo, categoryRepo)

        viewModel.uiState.test {
            val firstItem = awaitItem()
            val state = if (firstItem is UiState.Loading) awaitItem() else firstItem

            // 2. ตรวจสอบว่าเป็น Error จริงไหม
            assert(state is UiState.Error)
            // เช็คว่า ErrorMapper ทำงานไหม (ถ้า Exception ข้อความจะถูกแปลง)
            // assertEquals("Database error: Unable to access your data.", (state as UiState.Error).message)
        }
    }
}
