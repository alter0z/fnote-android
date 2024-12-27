package com.fiberlance.fnote.main.model

sealed class UiState<out R> private constructor() {
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val message: String): UiState<Nothing>()
    data object Loading: UiState<Nothing>()
}
