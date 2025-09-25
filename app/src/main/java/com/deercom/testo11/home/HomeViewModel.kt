package com.deercom.testo11.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val session: SessionStore
) : ViewModel() {

    val companyName = session.companyNameFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    val alias = session.aliasFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    fun logout(onDone: () -> Unit) {
        viewModelScope.launch {
            session.clearSessionOnly() // <- NO usar .clear()
            onDone()
        }
    }
}
