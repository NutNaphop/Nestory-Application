package com.naphop.nestory.navigation

import com.naphop.nestory.feature.box.navigation.Box
import com.naphop.nestory.feature.home.navigation.Home
import com.naphop.nestory.feature.item.navigation.Item
import com.naphop.nestory.feature.setting.navigation.Setting

class NavigationItem(
    val title: String,
    val activeIcon: Int,
    val inActiveIcon: Int,
    val route: String
)

val navigationItem = listOf<NavigationItem>(
    NavigationItem(
        title = "Home",
        activeIcon = Home.activeIcon,
        inActiveIcon = Home.inActiveIcon,
        route = Home.route
    ),
    NavigationItem(
        title = "Items",
        activeIcon = Item.activeIcon,
        inActiveIcon = Item.inActiveIcon,
        route = Item.route
    ),
    NavigationItem(
        title = "Boxes",
        activeIcon = Box.activeIcon,
        inActiveIcon = Box.inActiveIcon,
        route = Box.route
    ),
    NavigationItem(
        title = "Setting",
        activeIcon = Setting.activeIcon,
        inActiveIcon = Setting.inActiveIcon,
        route = Setting.route
    ),
)