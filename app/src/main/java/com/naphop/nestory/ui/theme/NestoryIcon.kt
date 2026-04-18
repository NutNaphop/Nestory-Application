package com.naphop.nestory.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.naphop.nestory.R

object NestoryIcons {
    // Navigation Icons (Active/Inactive)
    val HomeActive @Composable get() = painterResource(R.drawable.ic_nav_home_active)
    val HomeInactive @Composable get() = painterResource(R.drawable.ic_nav_home_inactive)
    
    val ItemActive @Composable get() = painterResource(R.drawable.ic_nav_item)
    val ItemInactive @Composable get() = painterResource(R.drawable.ic_nav_item_inactive)
    
    val BoxActive @Composable get() = painterResource(R.drawable.ic_nav_box)
    val BoxInactive @Composable get() = painterResource(R.drawable.ic_nav_box_inactive)
    
    val CategoryActive @Composable get() = painterResource(R.drawable.ic_nav_category)
    val CategoryInactive @Composable get() = painterResource(R.drawable.ic_category_outline) // หรือใช้ ic_nav_category ที่เป็น outline
    
    val SettingActive @Composable get() = painterResource(R.drawable.ic_nav_setting)
    val SettingInactive @Composable get() = painterResource(R.drawable.ic_nav_setting_inactive)

    // Action Icons
    val AddItem @Composable get() = painterResource(R.drawable.ic_item_add)
    val AddBox @Composable get() = painterResource(R.drawable.ic_box_add)
    val AddCategory @Composable get() = painterResource(R.drawable.ic_category_add)
    
    val Increase @Composable get() = painterResource(R.drawable.ic_increse)
    val Decrease @Composable get() = painterResource(R.drawable.ic_decrease)
    val DecreaseOutline @Composable get() = painterResource(R.drawable.ic_decrease_outline)
    
    val Edit @Composable get() = painterResource(R.drawable.ic_edit)
    val Delete @Composable get() = painterResource(R.drawable.ic_delete)
    val Save @Composable get() = painterResource(R.drawable.ic_bt_save)
    val Cancel @Composable get() = painterResource(R.drawable.ic_cancel)
    val Back @Composable get() = painterResource(R.drawable.ic_back)

    // Other UI Icons
    val Question @Composable get() = painterResource(R.drawable.ic_popup_question)
    val Danger @Composable get() = painterResource(R.drawable.ic_danger_triangle)
}
