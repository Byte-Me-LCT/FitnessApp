package com.byteme.fitness.domain.repository

import com.byteme.fitness.domain.model.profile.Profile

interface ProfileRepository {
    suspend fun save(profile: Profile): Result<Unit>
}