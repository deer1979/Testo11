package com.deercom.testo11.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

private val Context.ds by preferencesDataStore(name = "local_users")

@Singleton
class LocalUserRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object K {
        val USERS: Preferences.Key<Set<String>> = stringSetPreferencesKey("users_set")
        val CARGOS: Preferences.Key<Set<String>> = stringSetPreferencesKey("cargos_set")
        val LOCALIDADES: Preferences.Key<Set<String>> = stringSetPreferencesKey("localidades_set")
    }

    // -------- Encoding simple (sin libs externas) --------
    private fun encode(user: User): String =
        listOf(
            user.alias, user.nombres, user.apellidos, user.cargo, user.documento,
            user.telefonoPersonal, user.telefonoDispositivo, user.localidad, user.fotoUri, user.passwordHash
        ).joinToString(separator = "§") // separador poco común

    private fun decode(s: String): User {
        val parts = s.split("§")
        return User(
            alias = parts.getOrNull(0) ?: "",
            nombres = parts.getOrNull(1) ?: "",
            apellidos = parts.getOrNull(2) ?: "",
            cargo = parts.getOrNull(3) ?: "",
            documento = parts.getOrNull(4) ?: "",
            telefonoPersonal = parts.getOrNull(5) ?: "",
            telefonoDispositivo = parts.getOrNull(6) ?: "",
            localidad = parts.getOrNull(7) ?: "",
            fotoUri = parts.getOrNull(8) ?: "",
            passwordHash = parts.getOrNull(9) ?: ""
        )
    }

    fun usersFlow(): Flow<List<User>> =
        context.ds.data.map { it[K.USERS].orEmpty().map(::decode) }

    suspend fun upsertUser(u: User) {
        context.ds.edit { prefs ->
            val all = prefs[K.USERS].orEmpty().toMutableSet()
            // remove old with same alias
            val without = all.filterNot { decode(it).alias.equals(u.alias, ignoreCase = true) }.toMutableSet()
            without.add(encode(u))
            prefs[K.USERS] = without
            if (u.cargo.isNotBlank()) prefs[K.CARGOS] = prefs[K.CARGOS].orEmpty() + u.cargo
            if (u.localidad.isNotBlank()) prefs[K.LOCALIDADES] = prefs[K.LOCALIDADES].orEmpty() + u.localidad
        }
    }

    fun cargosFlow(): Flow<List<String>> =
        context.ds.data.map { it[K.CARGOS].orEmpty().sorted() }

    fun localidadesFlow(): Flow<List<String>> =
        context.ds.data.map { it[K.LOCALIDADES].orEmpty().sorted() }

    // Hash simple (modo pruebas) con SHA-256
    fun sha256(text: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(text.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

