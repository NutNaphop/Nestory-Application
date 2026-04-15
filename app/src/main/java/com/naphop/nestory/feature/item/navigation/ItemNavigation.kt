package com.naphop.nestory.feature.item.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naphop.nestory.R
import com.naphop.nestory.navigation.NavigationDestination

private const val ROUTE_NAME = "item_graph"

fun NavGraphBuilder.itemGraph(
    onNavigateToDetail : (String) -> Unit,
    onNavigationToEdit: (String) -> Unit
){
    navigation(
        startDestination = Item.route,
        route = ROUTE_NAME
    ){
        composable(route = Item.route){
            Text("Item Screen")
        }
    }
}

object Item: NavigationDestination{
    override val activeIcon: Int
        get() = R.drawable.ic_nav_item
    override val inActiveIcon: Int
        get() = R.drawable.ic_nav_item_inactive
    override val route: String
        get() = "item"
}