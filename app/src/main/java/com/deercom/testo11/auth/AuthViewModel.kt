package com.deercom.testo11.auth

import androidx.lifecycle.ViewModel
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val session: SessionStore
) : ViewModel() {

    /** Alias guardado en DataStore (para prellenar Login). */
    val aliasFlow: Flow<String> = session.aliasFlow

    /** Valida que el alias ingresado coincida con lo guardado. */
    suspend fun isAliasValid(input: String): Boolean {
        val saved = session.aliasFlow.first()
        return saved.isNotBlank() && input.equals(saved, ignoreCase = true)
    }

    // Nota: logout lo gestiona HomeViewModel con session.clearSessionOnly()
}
