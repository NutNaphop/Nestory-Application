package com.naphop.nestory.ui.components.inventory

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.naphop.nestory.ui.components.base.NestoryCard
import com.naphop.nestory.ui.theme.ExpiredBg
import com.naphop.nestory.ui.theme.GrayText
import com.naphop.nestory.ui.theme.NavyDark
import com.naphop.nestory.ui.theme.NestoryDimens
import com.naphop.nestory.ui.theme.NestoryIcons
import com.naphop.nestory.ui.theme.NestoryTypography
import com.naphop.nestory.ui.theme.StatusBadge
import com.naphop.nestory.ui.theme.WarningBg
import com.naphop.nestory.ui.theme.dropCard
import kotlin.math.roundToInt

enum class DragAnchors {
    Start,
    End,
}

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
        modifier
            .fillMaxWidth()
            .dropCard()
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableInventoryItem(
    name: String,
    dueDate: String,
    emoji: String,
    status: StatusBadge,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    category: String? = null,
    quantity: Int? = null,
) {
    val density = LocalDensity.current
    val actionsWidthPx = with(density) { 140.dp.toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = spring(),
            decayAnimationSpec = exponentialDecay()
        )
    }

    SideEffect {
        state.updateAnchors(
            DraggableAnchors {
                DragAnchors.Start at 0f
                DragAnchors.End at -actionsWidthPx
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .clip(MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(70.dp)
                    .background(WarningBg)
                    .clickable { onEdit() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = NestoryIcons.Edit,
                    contentDescription = "Edit",
                    tint = NavyDark,
                    modifier = Modifier.size(24.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(70.dp)
                    .background(ExpiredBg)
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = NestoryIcons.Delete,
                    contentDescription = "Delete",
                    tint = NavyDark,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(state.offset.roundToInt(), 0) }
                .anchoredDraggable(state, Orientation.Horizontal)
        ) {
            InventoryItemCard(
                name = name,
                dueDate = dueDate,
                emoji = emoji,
                status = status,
                category = category,
                quantity = quantity,
                showShadow = state.offset == 0f
            )
        }
    }
}
