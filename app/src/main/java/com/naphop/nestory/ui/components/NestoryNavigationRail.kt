package com.naphop.nestory.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.naphop.nestory.navigation.navigationItem

@Composable
fun NestoryNavigationRail(
    currentRoute: String?,
    onNavigateClick: (String) -> Unit
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxHeight().border(
            color = MaterialTheme.colorScheme.outline,
            width = 1.dp,
            shape = RectangleShape
        ),
        header = {
            Spacer(modifier = Modifier.size(48.dp))
        }
    ) {
        navigationItem.forEach { item ->
            val isSelected = currentRoute == item.route
            val currentIcon = if (isSelected) item.activeIcon else item.inActiveIcon
            
            NavigationRailItem(
                selected = isSelected,
                onClick = { onNavigateClick(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(currentIcon),
                        contentDescription = item.route,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier.padding(vertical = 12.dp),
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}
