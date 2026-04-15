package com.naphop.nestory.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

enum class StatusBadge {
    EXPIRED, SOON, OK
}

@Composable
fun StatusBadge(
    statusLabel: StatusBadge,
    modifier: Modifier = Modifier
) {
    val containerColor = when (statusLabel) {
        StatusBadge.SOON -> WarningBg
        StatusBadge.EXPIRED -> ExpiredBg
        StatusBadge.OK -> OkBg
    }

    val contentColor = when (statusLabel) {
        StatusBadge.SOON -> WarningOrange
        StatusBadge.EXPIRED -> ExpiredRed
        StatusBadge.OK -> OkGreen
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 0.5.dp,
                shape = RoundedCornerShape(20.dp),
                color = contentColor
            )
            .background(containerColor)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
            text = statusLabel.name,
            style = NestoryTypography.StatusLabel,
            color = contentColor
        )
    }
}
