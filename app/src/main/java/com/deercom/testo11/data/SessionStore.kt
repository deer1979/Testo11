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
    private val KEY_COMPANY_NAME = stringPreferencesKey("company_name")
    private val KEY_PROFILE_NAME = stringPreferencesKey("profile_name")
    private val KEY_PROFILE_PHONE = stringPreferencesKey("profile_phone")
    private val KEY_PROFILE_DOCID = stringPreferencesKey("profile_docid")

    val aliasFlow = ds.data.map { it[KEY_ALIAS].orEmpty() }
    val companyIdFlow = ds.data.map { it[KEY_COMPANY].orEmpty() }
    val companyNameFlow = ds.data.map { it[KEY_COMPANY_NAME].orEmpty() }
    val profileNameFlow = ds.data.map { it[KEY_PROFILE_NAME].orEmpty() }
    val profilePhoneFlow = ds.data.map { it[KEY_PROFILE_PHONE].orEmpty() }
    val profileDocIdFlow = ds.data.map { it[KEY_PROFILE_DOCID].orEmpty() }

    suspend fun setAlias(alias: String) = ds.edit { it[KEY_ALIAS] = alias }
    suspend fun setCompanyId(id: String) = ds.edit { it[KEY_COMPANY] = id }
    suspend fun setCompanyName(name: String) = ds.edit { it[KEY_COMPANY_NAME] = name }
    suspend fun setProfile(name: String, phone: String, docId: String) = ds.edit {
        it[KEY_PROFILE_NAME] = name
        it[KEY_PROFILE_PHONE] = phone
        it[KEY_PROFILE_DOCID] = docId
    }

    /** Cerrar sesión SIN borrar datos de empresa/perfil */
    suspend fun clearSessionOnly() = ds.edit { it.remove(KEY_ALIAS) }

    /** Borrar todo (útil para “reset app”) */
    suspend fun clearAll() = ds.edit { it.clear() }
}
