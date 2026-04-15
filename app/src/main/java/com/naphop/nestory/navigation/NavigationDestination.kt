package com.naphop.nestory.navigation

import android.graphics.drawable.Icon

interface NavigationDestination {
    val activeIcon : Int
    val inActiveIcon: Int
    val route: String
}