package com.deercom.testo11.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val session: SessionStore
) : ViewModel() {

    val name  = session.profileNameFlow .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")
    val phone = session.profilePhoneFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")
    val docId = session.profileDocIdFlow .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    fun save(name: String, phone: String, docId: String, onDone: () -> Unit) {
        viewModelScope.launch {
            session.setProfile(name.trim(), phone.trim(), docId.trim())
            onDone()
        }
    }
}


