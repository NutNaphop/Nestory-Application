package com.naphop.nestory.util

import androidx.compose.runtime.Composable
import com.naphop.nestory.ui.components.base.NestoryError
import com.naphop.nestory.ui.components.base.NestoryLoading

@Composable
fun <T> NestoryUiStateHandler(
    uiState: UiState<T>,onRetry: (() -> Unit)? = null,
    onSuccess: @Composable (T) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> NestoryLoading()
        is UiState.Error -> NestoryError(
            message = uiState.message,
            onRetry = onRetry
        )
        is UiState.Success -> onSuccess(uiState.data)
    }
}