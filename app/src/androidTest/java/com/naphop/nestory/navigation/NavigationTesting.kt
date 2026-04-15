package com.naphop.nestory.navigation

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.naphop.nestory.MainScreen
import com.naphop.nestory.feature.box.navigation.Box
import com.naphop.nestory.feature.home.navigation.Home
import com.naphop.nestory.feature.item.navigation.Item
import com.naphop.nestory.feature.setting.navigation.Setting
import com.naphop.nestory.ui.theme.NestoryTheme
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class NavigationTesting {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    private fun setUpScreen(screenSize: WindowWidthSizeClass) {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NestoryTheme {
                MainScreen(
                    windowWidthSizeClass = screenSize, // ใช้ค่าจาก parameter
                    navController = navController
                )
            }
        }
    }

    @Test
    fun startFromHomeScreen_shouldBeHomeScreen() {
        setUpScreen(WindowWidthSizeClass.Compact)
        assertEquals(Home.route, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun fromHomeScreen_clickItems_goToItem() {
        setUpScreen(WindowWidthSizeClass.Compact)
        val expectedRoute = Item.route
        composeTestRoute(expectedRoute)
    }

    @Test
    fun fromHomeScreen_clickBox_goToBox() {
        setUpScreen(WindowWidthSizeClass.Compact)
        val expectedRoute = Box.route
        composeTestRoute(expectedRoute)
    }

    @Test
    fun fromHomeScreen_clickSetting_goToSetting() {
        setUpScreen(WindowWidthSizeClass.Compact)
        val expectedRoute = Setting.route
        composeTestRoute(expectedRoute)
    }

    @Test
    fun tabletMode_navigationRail_shouldBeFunctional() {
        setUpScreen(WindowWidthSizeClass.Expanded)
        
        val expectedRoute = Box.route
        composeTestRule.onNodeWithContentDescription(expectedRoute).performClick()
        assertEquals(expectedRoute, navController.currentBackStackEntry?.destination?.route)
        composeTestRule.onNodeWithContentDescription(expectedRoute).assertIsSelected()
    }

    private fun composeTestRoute(expectedRoute: String) {
        composeTestRule.onNodeWithContentDescription(label = expectedRoute).performClick()
        val actualRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(expectedRoute, actualRoute)
    }
}
