package com.naphop.nestory.ui.mapper

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.util.TimeZone

class DateMapperTest {
    private val zoneId = ZoneId.systemDefault()

    @Test
    fun `set format date correctly`() {
        val baseTime = LocalDate
            .of(2025, 1, 1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val expect = "01/01/2025"
        val result = baseTime.toFormattedDate()

        assertEquals(expect,result)
    }

    @Test
    fun `send null toFormattedDate should return No expiration`(){
        val expect = "No expiration"
        val result = null.toExpiryDateString()

        assertEquals(expect,result)

    }

    @Test
    fun `format short date correctly`(){
        val baseTime = LocalDate
            .of(2025, 1, 1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val expect = "01 Jan 2025"
        val result = baseTime.toShortDate()

        assertEquals(expect,result)
    }
}