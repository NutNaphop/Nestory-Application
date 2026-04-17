package com.naphop.nestory.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.naphop.nestory.feature.box.navigation.boxGraph
import com.naphop.nestory.feature.home.navigation.homeGraph
import com.naphop.nestory.feature.item.navigation.itemGraph
import com.naphop.nestory.feature.setting.navigation.settingGraph


@Composable
fun NestoryNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home_graph",
    ) {
        homeGraph(
            navController = navController,
            onNavigateToDetail = {

            },
            onNavigationToEdit = {

            }
        )
        itemGraph(
            onNavigateToDetail = {

            },
            onNavigationToEdit = {

            }
        )
        boxGraph(
            onNavigateToDetail = {

            },
            onNavigationToEdit = {

            }
        )
        settingGraph(
            onNavigateToDetail = {

            },
            onNavigationToEdit = {

            }
        )
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }