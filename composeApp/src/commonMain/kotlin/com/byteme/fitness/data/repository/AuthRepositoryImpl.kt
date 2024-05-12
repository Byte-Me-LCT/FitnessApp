package com.byteme.fitness.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.byteme.fitness.domain.model.profile.AuthData
import com.byteme.fitness.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : AuthRepository {

    override suspend fun save(authData: AuthData): Result<Unit> {
        return runCatching {
            dataStore.edit { preferences ->
                val profileIdKey = getProfileIdKey(authData.profileId)
                preferences[profileIdKey] = authData.token
            }
        }
    }

    override suspend fun getTokenById(id: String): Result<String?> {
        return runCatching {
            val profileIdKey = getProfileIdKey(id)
            dataStore.data
                .map { preferences ->
                    preferences[profileIdKey]
                }
                .first()
        }
    }

    private fun getProfileIdKey(id: String): Preferences.Key<String> {
        return stringPreferencesKey(id)
    }
}