package com.byteme.fitness.core.utils

import platform.Foundation.NSUUID

actual fun randomUUID(): String {
    return  NSUUID().UUIDString()
}