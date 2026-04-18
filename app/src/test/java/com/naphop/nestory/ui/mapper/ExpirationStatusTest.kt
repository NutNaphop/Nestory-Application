package com.naphop.nestory.ui.mapper

import com.naphop.nestory.domain.model.ExpirationStatus
import com.naphop.nestory.ui.theme.StatusBadge
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExpirationStatusTest {
    @Test
    fun `send expiration EXPIRED then return EXPIRED`() {
        val expStatus = ExpirationStatus.EXPIRED

        val result = expStatus.toBadgeType()

        val expect = StatusBadge.EXPIRED
        assertEquals(expect, result)
    }

    @Test
    fun `send expiration THIS_WEEK then return SOON`() {
        val expStatus = ExpirationStatus.THIS_WEEK

        val result = expStatus.toBadgeType()

        val expect = StatusBadge.SOON
        assertEquals(expect, result)
    }

    @Test
    fun `send expiration THIS_MONTH then return SOON`() {
        val expStatus = ExpirationStatus.THIS_MONTH

        val result = expStatus.toBadgeType()

        val expect = StatusBadge.SOON
        assertEquals(expect, result)
    }

    @Test
    fun `send expiration FRESH then return OK`() {
        val expStatus = ExpirationStatus.FRESH

        val result = expStatus.toBadgeType()

        val expect = StatusBadge.OK
        assertEquals(expect, result)
    }
}