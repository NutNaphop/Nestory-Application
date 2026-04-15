package com.naphop.nestory.feature.home.navigation

import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naphop.nestory.R
import com.naphop.nestory.feature.home.HomeScreen
import com.naphop.nestory.navigation.NavigationDestination

private const val ROUTE_NAME = "home_graph"

fun NavGraphBuilder.homeGraph(
    onNavigateToDetail : (String) -> Unit,
    onNavigationToEdit: (String) -> Unit
){
    navigation(
        startDestination = Home.route,
        route = ROUTE_NAME
    ){
        composable(route = Home.route){
            HomeScreen()
        }
    }
}

object Home: NavigationDestination{
    override val activeIcon: Int
        get() = R.drawable.ic_nav_home_active
    override val inActiveIcon: Int
        get() = R.drawable.ic_nav_home_inactive
    override val route: String
        get() = "home"

}