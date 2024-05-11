package com.byteme.fitness.core.exceptions

sealed class AppException(open val throwable: Throwable? = null) : Exception(throwable) {

    class ProfileNotValid : AppException()
}