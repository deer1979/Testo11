package com.deercom.testo11.wizard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WizardViewModel @Inject constructor(
    private val session: SessionStore
) : ViewModel() {

    fun saveCompany(@Suppress("UNUSED_PARAMETER") name: String, onSaved: (String) -> Unit) {
        val id = UUID.randomUUID().toString()
        viewModelScope.launch {
            session.setCompanyId(id)
            onSaved(id)
        }
    }

    /** Guarda el alias y valida que quedó persistido antes de continuar. */
    fun saveAdminAlias(alias: String, onSaved: () -> Unit, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            session.setAlias(alias.trim())
            // Verificamos inmediatamente
            val persisted = session.aliasFlow.first()
            if (persisted.equals(alias.trim(), ignoreCase = true)) {
                onSaved()
            } else {
                onError("No se pudo confirmar el guardado del alias.")
            }
        }
    }
}
