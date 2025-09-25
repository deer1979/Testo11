package com.deercom.testo11.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStore @Inject constructor(
    private val ds: DataStore<Preferences>
) {
    private val KEY_ALIAS = stringPreferencesKey("alias")
    private val KEY_COMPANY = stringPreferencesKey("company_id")

    val aliasFlow = ds.data.map { it[KEY_ALIAS].orEmpty() }
    val companyIdFlow = ds.data.map { it[KEY_COMPANY].orEmpty() }

    suspend fun setAlias(v: String) = ds.edit { it[KEY_ALIAS] = v }
    suspend fun setCompanyId(v: String) = ds.edit { it[KEY_COMPANY] = v }
    suspend fun clear() = ds.edit { it.clear() }
}


