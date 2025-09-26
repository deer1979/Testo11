@@
 package com.deercom.testo11.onboarding
 
 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.viewModelScope
 import com.deercom.testo11.data.LocalUserRepository
 import com.deercom.testo11.data.User
 import dagger.hilt.android.lifecycle.HiltViewModel
 import kotlinx.coroutines.launch
 import javax.inject.Inject
 
 @HiltViewModel
 class OnboardingViewModel @Inject constructor(
     private val repo: LocalUserRepository
 ) : ViewModel() {
-    // empresa
    // empresa
     var companyName: String = ""
     var companyId: String = ""
 
     // perfil
     var firstName: String = ""
     var lastName: String = ""
     var phone: String = ""
 
     // seguridad
     var username: String = ""
     var password: String = ""
 
     // foto (uri texto por simplicidad)
     var photoUri: String? = null
 
-    fun savePartial() { /* ya no se usa en v2.1 */ }
    enum class Section { EMPRESA, PERFIL, SEGURIDAD, FOTO }
    var current: Section = Section.EMPRESA
        private set

    fun next() {
        current = when (current) {
            Section.EMPRESA -> Section.PERFIL
            Section.PERFIL -> Section.SEGURIDAD
            Section.SEGURIDAD -> Section.FOTO
            Section.FOTO -> Section.FOTO
        }
    }

    fun back() {
        current = when (current) {
            Section.EMPRESA -> Section.EMPRESA
            Section.PERFIL -> Section.EMPRESA
            Section.SEGURIDAD -> Section.PERFIL
            Section.FOTO -> Section.SEGURIDAD
        }
    }

    fun goToSection(s: Section) { current = s }
 
-    fun saveFinal(onDone: () -> Unit) {
    fun saveAll(onDone: () -> Unit) {
         viewModelScope.launch {
             // crea usuario admin master mínimo con username/password y nombre
             val user = User(
                 id = username.ifBlank { "admin" },
                 displayName = listOf(firstName, lastName).joinToString(" ").trim().ifBlank { "Admin" },
                 phone = phone.ifBlank { null },
                 role = "Admin Master"
             )
             repo.addUser(user)
            repo.setSetupDone(true)
             onDone()
         }
     }
 }
