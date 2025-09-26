package com.deercom.testo11.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.LocalUserRepository
import com.deercom.testo11.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.Normalizer
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repo: LocalUserRepository
) : ViewModel() {

    data class UiState(
        val empresaNombre: String = "",
        val ruc: String = "",
        val localidad: String = "",
        val alias: String = "",
        val nombres: String = "",
        val apellidos: String = "",
        val cargo: String = "",
        val documento: String = "",
        val telefonoPersonal: String = "",
        val telefonoDispositivo: String = "",
        val fotoUri: String = "",
        val password: String = "",
        val passwordConfirm: String = ""
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun update(transform: (UiState) -> UiState) {
        _state.value = transform(_state.value)
    }

    fun regenerateAlias() {
        val s = _state.value
        val initial = s.nombres.trim().firstOrNull()?.lowercase() ?: ""
        val apellido = s.apellidos.trim().split(" ").firstOrNull()?.lowercase().orEmpty()
        val loc = s.localidad.trim().lowercase()
        val base = (initial + apellido + if (loc.isNotBlank()) ".$loc" else "")
        val normalized = Normalizer.normalize(base, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .replace("[^a-z0-9._]".toRegex(), "")
        update { it.copy(alias = normalized) }
    }

    fun save(onSaved: () -> Unit) {
        val s = _state.value
        viewModelScope.launch {
            val user = User(
                alias = s.alias.ifBlank { "usuario" },
                nombres = s.nombres,
                apellidos = s.apellidos,
                cargo = s.cargo,
                documento = s.documento,
                telefonoPersonal = s.telefonoPersonal,
                telefonoDispositivo = s.telefonoDispositivo,
                localidad = s.localidad,
                fotoUri = s.fotoUri,
                passwordHash = repo.sha256(s.password)
            )
            repo.upsertUser(user)
            onSaved()
        }
    }
}

