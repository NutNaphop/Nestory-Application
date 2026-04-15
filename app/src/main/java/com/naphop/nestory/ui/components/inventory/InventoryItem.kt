package com.naphop.nestory.ui.components.inventory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.naphop.nestory.ui.components.NestoryCard
import com.naphop.nestory.ui.theme.*

/**
 * Common Thumbnail for Inventory Items
 */
@Composable
internal fun ItemThumbnail(
    emoji: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(text = emoji, style = NestoryTypography.HeaderLarge)
    }
}

/**
 * Master Inventory Item Card.
 * Flexible for Home (Compact) and Inventory List (Full).
 */
@Composable
fun InventoryItemCard(
    name: String,
    dueDate: String,
    emoji: String,
    status: StatusBadge,
    modifier: Modifier = Modifier,
    category: String? = null,
    quantity: Int? = null,
    showShadow: Boolean = true
) {
    val cardModifier = if (showShadow) {
        modifier.fillMaxWidth().dropCard()
    } else {
        modifier.fillMaxWidth()
    }

    NestoryCard(
        modifier = cardModifier,
        paddingVertical = 16.dp,
        paddingHorizontal = 16.dp,
        containerColor = Color.White,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemThumbnail(emoji = emoji)

            Spacer(modifier = Modifier.width(NestoryDimens.Medium))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = name, style = NestoryTypography.TitleMedium)
                    if (category != null) {
                        Text(
                            text = " • $category",
                            style = NestoryTypography.BodyMedium,
                            color = GrayText
                        )
                    }
                }
                Text(text = dueDate, style = NestoryTypography.BodyMedium, color = GrayText)
            }

            Column(horizontalAlignment = Alignment.End) {
                if (quantity != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = quantity.toString(),
                            style = NestoryTypography.TitleMedium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Remaining",
                            style = NestoryTypography.LabelSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                StatusBadge(statusLabel = status)
            }
        }
    }
}
