package com.naphop.nestory.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naphop.nestory.R
import com.naphop.nestory.data.local.EmojiProvider
import com.naphop.nestory.feature.home.component.ScreenHeader
import com.naphop.nestory.ui.components.NestoryCard
import com.naphop.nestory.ui.components.inventory.InventoryItemCard
import com.naphop.nestory.ui.theme.NestoryDimens
import com.naphop.nestory.ui.theme.NestoryTheme
import com.naphop.nestory.ui.theme.NestoryTypography
import com.naphop.nestory.ui.theme.StatusBadge
import com.naphop.nestory.ui.theme.dropCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetail: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenHeader(
                icon = painterResource(R.drawable.ic_nav_home_active),
                title = "Good Morning!",
                description = "Everything is in its place"
            )
            Spacer(modifier = Modifier.height(33.dp))
            SummaryCard()
            Spacer(modifier = Modifier.height(33.dp))
            ExpiredSoonSection()
        }
    }
}

@Composable
fun SummaryCard() {
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
                    text = "48",
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
                        text = "3",
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(text = "category", style = NestoryTypography.BodyLarge)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        "3",
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text("Boxes", style = NestoryTypography.BodyLarge)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        "3",
                        style = NestoryTypography.BodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text("Categories", style = NestoryTypography.BodyLarge)
                }
            }
        }
    }
}

@Composable
fun ExpiredSoonSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Expiring Soon",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = "VIew All",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        InventoryItemCard(
            name = "KU MILK",
            quantity = 1,
            dueDate = "25 Apr",
            emoji = EmojiProvider.allEmojis.first(),
            status = StatusBadge.SOON,
        )

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    NestoryTheme() {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}