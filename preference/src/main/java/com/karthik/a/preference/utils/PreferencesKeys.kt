package com.karthik.a.preference.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val NAME_KEY = stringPreferencesKey("name")
    val ANOTHER_KEY = intPreferencesKey("another_key")
    val IS_LOGGED_IN_KEY = booleanPreferencesKey("isLoggedIn")
}