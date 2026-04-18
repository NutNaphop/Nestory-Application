package com.naphop.nestory.ui.mapper

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.sql.SQLException

class ErrorMapperTest {
    @Test
    fun `throw SQLException then return Database error`() {
        val exception = SQLException()
        val result = exception.toUserMessage()
        val expect = "Database error: Unable to access your data."

        assertEquals(expect, result)
    }

    @Test
    fun `throw custom exception then return localized message`() {
        val exception = Exception("Custom error message")
        val result = exception.toUserMessage()
        val expect = "Custom error message"

        assertEquals(expect, result)
    }

    @Test
    fun `throw exception then return default message`() {
        val exception = Exception()
        val result = exception.toUserMessage()
        val expect = "An unexpected error occurred. Please try again."

        assertEquals(expect, result)
    }
}
