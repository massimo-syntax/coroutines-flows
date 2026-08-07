package com.example.data.remote

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val error: String) : Response<Nothing>()
}