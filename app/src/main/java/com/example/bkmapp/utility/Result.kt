package com.example.bkmapp.utility

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()

    data class Failed(val error: Throwable): Result<Nothing>()

    object Loading : Result<Nothing>()

    fun isSuccess(): Boolean {
        return this is Success
    }

    fun isFailed(): Boolean {
        return this is Failed
    }

    fun isLoading(): Boolean {
        return this is Loading
    }

    fun getOrNull(): T? {
        return when(this){
            is Success -> value
            else -> null
        }
    }

    fun getOrThrow(): T {
        return when(this){
            is Success -> value
            is Failed -> throw error
            else -> throw IllegalStateException("Result is in Loading state")
        }
    }
}
