package com.naphop.nestory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naphop.nestory.navigation.NestoryNavGraph
import com.naphop.nestory.navigation.navigationItem
import com.naphop.nestory.ui.components.navigation.NestoryNavigationBar
import com.naphop.nestory.ui.components.navigation.NestoryNavigationRail
import com.naphop.nestory.ui.theme.NestoryTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            NestoryTheme {
                MainScreen(windowWidthSizeClass = windowSizeClass.widthSizeClass)
            }
        }
    }
}

@Composable
fun MainScreen(
    windowWidthSizeClass: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isTopLevelDestination = navigationItem.any { it.route == currentRoute }
    val useNavRail = windowWidthSizeClass != WindowWidthSizeClass.Compact

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (useNavRail && isTopLevelDestination) {
            NestoryNavigationRail(
                currentRoute = currentRoute,
                onNavigateClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        Scaffold(
            bottomBar = {
                if (!useNavRail && isTopLevelDestination) {
                    NestoryNavigationBar(
                        currentRoute = currentRoute,
                        onNavigateClick = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            Surface(
                color = Color.Transparent,
                modifier = Modifier.padding(innerPadding)
            ) {
                NestoryNavGraph(
                    navController = navController
                )
            }
        }
    }
}
