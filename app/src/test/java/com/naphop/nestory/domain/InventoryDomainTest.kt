package com.naphop.nestory.domain

import com.naphop.nestory.domain.model.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId

class InventoryDomainTest {
    private val zoneId = ZoneId.systemDefault()
    private val baseDate = LocalDate.of(2025, 1, 1)
    private val currentTime = baseDate.atStartOfDay(zoneId).toInstant().toEpochMilli()

    private val oneDay = 24 * 60 * 60 * 1000L

    private val category = Category(id = 1, syncId = "c1", name = "Food", icon = "🍎")
    private val box = Box(id = 1, syncId = "b1", boxName = "Kitchen", icon = "🏠")
    private val baseInv = Inventory(1, "s1", "Pizza", 1, category, box, null)

    @Test
    fun getExpirationStatus_whenDateIsYesterday_thenReturnExpired() {
        val expiredDate = currentTime - oneDay
        val testInventory = baseInv.copy(dueDate = expiredDate)

        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.EXPIRED, result)
    }

    @Test
    fun getExpirationStatus_whenDateIsToday_thenReturnThisWeek() {
        val testInventory = baseInv.copy(dueDate = currentTime)
        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.THIS_WEEK, result)
    }

    @Test
    fun getExpirationStatus_within7Days_thenReturnThisWeek() {
        val futureDate = currentTime + (6 * oneDay)
        val testInventory = baseInv.copy(dueDate = futureDate)

        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.THIS_WEEK, result)
    }

    @Test
    fun getExpirationStatus_within30Days_thenReturnThisMonth() {
        val futureDate = currentTime + (20 * oneDay)
        val testInventory = baseInv.copy(dueDate = futureDate)

        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.THIS_MONTH, result)
    }

    @Test
    fun getExpirationStatus_moreThan30Days_thenReturnFresh() {
        val futureDate = currentTime + (45 * oneDay)
        val testInventory = baseInv.copy(dueDate = futureDate)

        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.FRESH, result)
    }

    @Test
    fun getExpirationStatus_whenDateIsNull_thenReturnFresh() {
        val testInventory = baseInv.copy(dueDate = null)

        val result = testInventory.getExpirationStatus(currentTime)

        assertEquals(ExpirationStatus.FRESH, result)
    }
}
