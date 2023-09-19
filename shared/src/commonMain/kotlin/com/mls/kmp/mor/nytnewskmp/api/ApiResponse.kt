package com.mls.kmp.mor.nytnewskmp.api

sealed class ApiResponse<out T> {

    data class Success<out T>(
        val data: T
    ): ApiResponse<T>()

    data class Failure<out T>(
        val error: Exception
    ): ApiResponse<T>()
}