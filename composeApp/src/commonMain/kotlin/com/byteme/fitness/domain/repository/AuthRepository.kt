package com.byteme.fitness.domain.repository

import com.byteme.fitness.domain.model.profile.AuthData

interface AuthRepository {
    suspend fun save(authData: AuthData): Result<Unit>
    suspend fun getTokenById(id: String): Result<String?>
}