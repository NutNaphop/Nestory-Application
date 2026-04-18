package com.naphop.nestory.ui.mapper

import java.sql.SQLException

/**
 * Maps technical exceptions to user-friendly messages for the UI.
 */
fun Throwable.toUserMessage(): String {
    return when (this) {
        is SQLException -> "Database error: Unable to access your data."
        // Future cases like IOException for network can be added here
        else -> this.localizedMessage ?: "An unexpected error occurred. Please try again."
    }
}
