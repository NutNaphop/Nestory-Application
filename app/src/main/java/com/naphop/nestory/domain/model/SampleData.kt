package com.naphop.nestory.domain.model

import java.util.Calendar

object SampleData {
    private fun getRelativeDate(days: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar.timeInMillis
    }

    val categories = listOf(
        Category(1, "c1", "Food", "🥛"),
        Category(2, "c2", "Household", "🧼"),
        Category(3, "c3", "Tools", "🔋"),
        Category(4, "c4", "Health", "💊")
    )

    val inventoryList = listOf(
        Inventory(1, "s1", "KU Milk", 2, categories[0], null, getRelativeDate(-2)), // หมดอายุแล้ว
        Inventory(2, "s2", "Apple", 5, categories[0], null, getRelativeDate(2)),  // ใกล้หมดอายุ (Soon)
        Inventory(3, "s3", "Bread", 1, categories[0], null, getRelativeDate(5)),  // ใกล้หมดอายุ (Soon)
        Inventory(4, "s4", "Soap", 3, categories[1], null, getRelativeDate(20)), // ปกติ (OK)
        Inventory(5, "s5", "Battery", 10, categories[2], null, getRelativeDate(100)), // ปกติ (OK)
        Inventory(6, "s6", "Chicken", 1, categories[0], null, getRelativeDate(-1)), // หมดอายุแล้ว
        Inventory(7, "s7", "Tomato", 4, categories[0], null, getRelativeDate(3)),  // ใกล้หมดอายุ (Soon)
        Inventory(8, "s8", "Medicine", 1, categories[3], null, getRelativeDate(15)), // ปกติ (OK)
        Inventory(9, "s9", "Egg", 12, categories[0], null, getRelativeDate(7)),   // ใกล้หมดอายุ (Soon)
        Inventory(10, "s10", "Hammer", 1, categories[2], null, null)            // ไม่มีวันหมดอายุ
    )
}
