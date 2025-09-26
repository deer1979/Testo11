@@
 package com.deercom.testo11.data
 
 import android.content.Context
 import androidx.datastore.preferences.core.Preferences
 import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
 import androidx.datastore.preferences.core.stringPreferencesKey
 import androidx.datastore.preferences.preferencesDataStore
 import dagger.hilt.android.qualifiers.ApplicationContext
 import kotlinx.coroutines.flow.Flow
 import kotlinx.coroutines.flow.map
@@
 private val Context.userPrefs by preferencesDataStore("users_prefs")
 
 class LocalUserRepository @Inject constructor(
     @ApplicationContext private val context: Context
 ) {
     private object K {
         val USERS: Preferences.Key<String> = stringPreferencesKey("users_json")
        val SETUP_DONE: Preferences.Key<Boolean> = booleanPreferencesKey("setup_done")
     }
@@
     suspend fun addUser(u: User) {
         val current = users()
         val updated = current + u
         context.userPrefs.edit { it[K.USERS] = toJson(updated) }
     }

    fun hasAnyUser(): Flow<Boolean> = context.userPrefs.data.map { prefs ->
        val json = prefs[K.USERS]
        !json.isNullOrEmpty() && fromJson(json).isNotEmpty()
    }

    fun isSetupDone(): Flow<Boolean> =
        context.userPrefs.data.map { prefs -> prefs[K.SETUP_DONE] ?: false }

    suspend fun setSetupDone(value: Boolean) {
        context.userPrefs.edit { it[K.SETUP_DONE] = value }
    }
 }
