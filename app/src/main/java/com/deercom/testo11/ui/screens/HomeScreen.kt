package com.deercom.testo11.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onLogout: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            ListItem(
                headlineContent = { Text("Bienvenido") },
                supportingContent = { Text("Sesión iniciada (placeholder)") },
                leadingContent = { Icon(Icons.Default.Home, null) }
            )
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = onLogout) { Text("Cerrar sesión") }
        }
    }
}


