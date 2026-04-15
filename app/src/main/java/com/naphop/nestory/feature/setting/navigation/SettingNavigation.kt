package com.naphop.nestory.feature.setting.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naphop.nestory.R
import com.naphop.nestory.navigation.NavigationDestination

private const val ROUTE_NAME = "setting_graph"

fun NavGraphBuilder.settingGraph(
    onNavigateToDetail : (String) -> Unit,
    onNavigationToEdit: (String) -> Unit
){
    navigation(
        startDestination = Setting.route,
        route = ROUTE_NAME
    ){
        composable(route = Setting.route){
            Text("Setting Screen")
        }
    }
}

object Setting: NavigationDestination{
    override val activeIcon: Int
        get() = R.drawable.ic_nav_setting
    override val inActiveIcon: Int
        get() = R.drawable.ic_nav_setting_inactive
    override val route: String
        get() = "setting"
}