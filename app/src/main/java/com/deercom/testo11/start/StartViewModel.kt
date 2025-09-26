package com.deercom.testo11.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deercom.testo11.data.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    session: SessionStore
) : ViewModel() {

    val companyName = session.companyNameFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    val alias = session.aliasFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")
}


