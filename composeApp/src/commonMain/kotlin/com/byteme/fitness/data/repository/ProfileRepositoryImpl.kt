package com.byteme.fitness.data.repository

import com.byteme.fitness.FitnessDatabase
import com.byteme.fitness.domain.model.profile.Profile
import com.byteme.fitness.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val database: FitnessDatabase
) : ProfileRepository {

    private val queries
        get() = database.fitnessDatabaseQueries

    override suspend fun save(profile: Profile): Result<Unit> {
        return runCatching {
            queries.insertOrReplace(
                id = profile.id,
                name = profile.name,
                initialHeight = profile.height,
                initialWeight = profile.weight
            )
        }
    }
}