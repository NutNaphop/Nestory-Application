package com.naphop.nestory.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naphop.nestory.navigation.navigationItem
import com.naphop.nestory.ui.theme.NestoryTheme

@Composable
fun NestoryNavigationBar(
    currentRoute: String?,
    onNavigateClick: (String) -> Unit = {}
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = MaterialTheme.colorScheme.outline,
                width = 1.dp,
                shape = RectangleShape
            ).navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navigationItem.forEach {
                val isSelected = currentRoute == it.route
                val currentIcon = if (isSelected) it.activeIcon else it.inActiveIcon
                NavIcon(
                    icon = painterResource(currentIcon),
                    contentDes = it.route,
                    active = isSelected,
                    onClick = {
                        onNavigateClick.invoke(it.route)
                    }
                )
            }
        }
    }
}

@Composable
fun NavIcon(
    icon: Painter,
    contentDes: String?,
    active: Boolean,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
            )
    ) {
        IconButton(
            onClick = onClick,
            enabled = true,
            colors = IconButtonColors(
                contentColor = if (active) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                containerColor = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.background
            ),
        ) {
            Icon(
                painter = icon,
                modifier = Modifier
                    .size(24.dp),
                contentDescription = contentDes,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NestoryNavigationBarPreview() {
    val currentRoute = "home"
    NestoryTheme {
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {

            NestoryNavigationBar(currentRoute)
        }
    }
}
