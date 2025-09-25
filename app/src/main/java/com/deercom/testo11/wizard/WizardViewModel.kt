package com.deercom.testo11.wizard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WizardViewModel @Inject constructor(
    private val session: SessionStore
) : ViewModel() {

    fun saveCompany(name: String, onSaved: (String) -> Unit) {
        // por ahora generamos un id local
        val id = UUID.randomUUID().toString()
        viewModelScope.launch {
            session.setCompanyId(id)
            onSaved(id)
        }
    }

    fun saveAdminAlias(alias: String, onSaved: () -> Unit) {
        viewModelScope.launch {
            session.setAlias(alias)
            onSaved()
        }
    }
}


