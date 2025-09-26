@@
 package com.deercom.testo11.onboarding
 
 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.viewModelScope
 import com.deercom.testo11.data.LocalUserRepository
 import com.deercom.testo11.data.User
 import dagger.hilt.android.lifecycle.HiltViewModel
 import kotlinx.coroutines.launch
 import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
 
 @HiltViewModel
 class OnboardingViewModel @Inject constructor(
     private val repo: LocalUserRepository
 ) : ViewModel() {
@@
     var photoUri: String? = null
 
-    fun savePartial() { /* en PRs siguientes */ }
    fun savePartial() { /* en PRs siguientes */ }
 
-    fun saveFinal(onDone: () -> Unit) {
    fun saveFinal(onDone: () -> Unit) {
         viewModelScope.launch {
             // crea usuario mínimo admin master
             val user = User(
                 id = username.ifBlank { "admin" },
                 displayName = listOf(firstName, lastName).joinToString(" ").trim().ifBlank { "Admin" },
                 phone = phone.ifBlank { null },
                 role = "Admin Master"
             )
             repo.addUser(user)
             onDone()
         }
     }

    // --- PR #1: bandera de setup
    fun markSetupDone() {
        viewModelScope.launch { repo.setSetupDone(true) }
    }
    fun setupDoneFlow(): Flow<Boolean> = repo.isSetupDone()
 }
