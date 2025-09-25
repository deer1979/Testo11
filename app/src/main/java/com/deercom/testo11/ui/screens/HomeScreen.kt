package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.auth.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val vm = hiltViewModel<AuthViewModel>()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ListItem(
                headlineContent = { Text("Bienvenido") },
                supportingContent = { Text("Sesión iniciada (placeholder)") },
                leadingContent = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        vm.logout()
                        onLogout()
                    }
                }
            ) { Text("Cerrar sesión") }
        }
    }
}
