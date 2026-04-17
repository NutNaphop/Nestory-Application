package com.naphop.nestory.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naphop.nestory.R
import com.naphop.nestory.domain.model.Inventory
import com.naphop.nestory.domain.model.getExpirationStatus
import com.naphop.nestory.feature.home.component.ScreenHeader
import com.naphop.nestory.ui.components.base.NestoryCard
import com.naphop.nestory.ui.components.inventory.SwipeableInventoryItem
import com.naphop.nestory.ui.mapper.toBadgeType
import com.naphop.nestory.ui.mapper.toShortDate
import com.naphop.nestory.ui.theme.NestoryDimens
import com.naphop.nestory.ui.theme.NestoryTheme
import com.naphop.nestory.ui.theme.NestoryTypography
import com.naphop.nestory.ui.theme.dropCard
import com.naphop.nestory.util.NestoryUiStateHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onViewClick: () -> Unit = {},
    onNavigateToDetail: () -> Unit = {},
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NestoryUiStateHandler(
            uiState = uiState,
            onRetry = {}
        ) { data ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenHeader(
                    icon = painterResource(R.drawable.ic_nav_home_active),
                    title = "Good Morning!",
                    description = "Everything is in its place"
                )
                Spacer(modifier = Modifier.height(33.dp))
                SummaryCard(
                    countItem = data.countItem,
                    countExpiring = data.countExpiring,
                    countBox = data.countBox,
                    countCategory = data.countCategory
                )
                Spacer(modifier = Modifier.height(33.dp))
                ExpiredSoonSection(
                    items = data.expiredItems,
                    onViewClick = onViewClick
                )
            }
        }

    }
}


@Composable
fun SummaryCard(
    countItem: Int,
    countExpiring: Int,
    countBox: Int,
    countCategory: Int
) {
    NestoryCard(
        modifier = Modifier
            .fillMaxWidth()
            .dropCard(),
        shape = RoundedCornerShape(NestoryDimens.ExtraLarge),
        border = null,
        paddingHorizontal = 20.dp,
        paddingVertical = 30.dp,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = countItem.toString(),
                    style = NestoryTypography.DisplayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = "Total Item",
                    style = NestoryTypography.BodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = countExpiring.toString(),
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(text = "Expiring Soon", style = NestoryTypography.BodyLarge)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        countBox.toString(),
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text("Boxes", style = NestoryTypography.BodyLarge)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        countCategory.toString(),
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text("Categories", style = NestoryTypography.BodyLarge)
                }
            }
        }
    }
}

@Composable
fun ExpiredSoonSection(
    items: List<Inventory>,
    onViewClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Expiring Soon",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .clickable(onClick = onViewClick)
                    .padding(8.dp),
                textAlign = TextAlign.End,
                text = "View All",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (items.isEmpty()) {
            Text(
                text = "No items expiring soon 🎉",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                textAlign = TextAlign.Center,
                style = NestoryTypography.BodyMedium
            )
        } else {
            items.forEach { item ->
                SwipeableInventoryItem(
                    name = item.name,
                    category = item.category?.name,
                    quantity = item.amount,
                    dueDate = item.dueDate?.toShortDate() ?: "",
                    emoji = item.category?.icon ?: "📦",
                    status = item.getExpirationStatus(currentTime = System.currentTimeMillis())
                        .toBadgeType(),
                    onEdit = {},
                    onDelete = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    NestoryTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}
