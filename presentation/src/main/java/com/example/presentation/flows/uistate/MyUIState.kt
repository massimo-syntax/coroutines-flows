package com.example.coroutinesflows.presentation.flows.uistate

sealed class MyUIState<out T>{
    object Loading : MyUIState<Nothing>()
    data class Error(val error: String) : MyUIState<Nothing>()
    data class Success<T>(val data: T) : MyUIState<T>()

}