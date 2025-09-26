package com.deercom.testo11.users

import androidx.lifecycle.ViewModel
import com.deercom.testo11.data.LocalUserRepository
import com.deercom.testo11.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    repo: LocalUserRepository
) : ViewModel() {
    val users: StateFlow<List<User>> =
        repo.usersFlow().map { it.sortedBy { u -> u.alias } }
            .stateIn(kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main), SharingStarted.Lazily, emptyList())
}

