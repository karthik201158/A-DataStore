package com.karthik.a.preference.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


// Extension function to set a String value in DataStore
suspend fun DataStore<Preferences>.setString(key: Preferences.Key<String>, value: String) {
    this.edit { preferences ->
        preferences[key] = value
    }
}

// Extension function to get a String value from DataStore
fun DataStore<Preferences>.getString(key: Preferences.Key<String>, default: String = ""): Flow<String> {
    return this.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                //FirebaseCrashlytics.getInstance().recordException(it)
                throw exception
            }
        }
        .map { preferences ->
            preferences[key] ?: default
        }
}

// Extension function to delete a String value from DataStore
suspend fun DataStore<Preferences>.deleteString(key:  Preferences.Key<String>) {
    this.edit { preferences ->
        preferences.remove(key)
    }
}