package com.naphop.nestory.feature.box.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naphop.nestory.R
import com.naphop.nestory.navigation.NavigationDestination

private const val ROUTE_NAME = "box_graph"

fun NavGraphBuilder.boxGraph(
    onNavigateToDetail : (String) -> Unit,
    onNavigationToEdit: (String) -> Unit
){
    navigation(
        startDestination = Box.route,
        route = ROUTE_NAME
    ){
        composable(route = Box.route){
            Text("Box Screen")
        }
    }
}

object Box: NavigationDestination{
    override val activeIcon: Int
        get() = R.drawable.ic_nav_box
    override val inActiveIcon: Int
        get() = R.drawable.ic_nav_box_inactive
    override val route: String
        get() = "box"

}