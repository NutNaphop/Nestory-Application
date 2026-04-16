package com.naphop.nestory.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naphop.nestory.R
import com.naphop.nestory.feature.home.ExpirationScreen
import com.naphop.nestory.feature.home.HomeScreen
import com.naphop.nestory.navigation.NavigationDestination


private const val HOME_GRAPH_ROUTE = "home_graph"
private const val HOME_ROUTE = "home"
private const val EXPIRING_ROUTE = "home_expiring" // เปลี่ยนให้ไม่ซ้ำ

fun NavController.navigateToExpirationList() {
    this.navigate(EXPIRING_ROUTE)
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    onNavigateToDetail: (String) -> Unit,
    onNavigationToEdit: (String) -> Unit
) {
    navigation(
        startDestination = Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onViewClick = {
                    navController.navigateToExpirationList()
                }
            )
        }

        composable(route = EXPIRING_ROUTE) {
            ExpirationScreen(
                onBackClick = { navController.popBackStack() },
                onItemEdit = { },
                onItemDelete = { }
            )
        }
    }
}
object Home : NavigationDestination {
    override val activeIcon = R.drawable.ic_nav_home_active
    override val inActiveIcon = R.drawable.ic_nav_home_inactive
    override val route = HOME_ROUTE
}