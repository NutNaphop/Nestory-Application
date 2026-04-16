package com.naphop.nestory.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.model.getExpirationStatus
import com.naphop.nestory.ui.components.NestoryTopBar
import com.naphop.nestory.ui.components.inventory.SwipeableInventoryItem
import com.naphop.nestory.ui.mapper.toBadgeType
import com.naphop.nestory.ui.mapper.toShortDate
import com.naphop.nestory.ui.theme.NestoryTypography
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpirationScreen(
    viewModel: ExpirationViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onItemEdit: (Int) -> Unit,
    onItemDelete: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentTime = System.currentTimeMillis()

    Scaffold(
        topBar = {
            NestoryTopBar(
                title = "Expiration List",
                onBack = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 1. Already Expired section
            if (uiState.expiredItems.isNotEmpty()) {
                stickyHeader {
                    SectionHeader(text = "Already Expired")
                }
                items(items = uiState.expiredItems, key = { it.id }) {
                    ExpirationItemRow(it, currentTime, onItemEdit, onItemDelete)
                }
            }

            // 2. Expiring This Week section
            if (uiState.expiringThisWeek.isNotEmpty()) {
                stickyHeader {
                    SectionHeader(text = "Expiring This Week")
                }
                items(items = uiState.expiringThisWeek, key = { it.id }) {
                    ExpirationItemRow(it, currentTime, onItemEdit, onItemDelete)
                }
            }

            // 3. Expiring This Month section
            if (uiState.expiringThisMonth.isNotEmpty()) {
                stickyHeader {
                    SectionHeader(text = "Expiring This Month")
                }
                items(items = uiState.expiringThisMonth, key = { it.id }) {
                    ExpirationItemRow(it, currentTime, onItemEdit, onItemDelete)
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = NestoryTypography.LabelHeader,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp)
    )
}

@Composable
private fun ExpirationItemRow(
    item: Inventory,
    currentTime: Long,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    SwipeableInventoryItem(
        name = item.name,
        quantity = item.amount,
        dueDate = item.dueDate?.toShortDate() ?: "",
        emoji = item.category?.icon ?: "📦",
        status = item.getExpirationStatus(currentTime).toBadgeType(),
        onEdit = { onEdit(item.id) },
        onDelete = { onDelete(item.id) }
    )
}
