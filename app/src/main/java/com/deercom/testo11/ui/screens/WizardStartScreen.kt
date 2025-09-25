package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardStartScreen(onNext: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Configuración inicial") }) }) { pad ->
        ElevatedCard(Modifier.padding(pad).padding(16.dp)) {
            ListItem(
                headlineContent = { Text("Bienvenido") },
                supportingContent = { Text("Inicia el asistente para crear tu empresa y el Admin Master.") },
                leadingContent = { Icon(Icons.Default.Flag, contentDescription = null) }
            )
            Button(onClick = onNext, modifier = Modifier.padding(16.dp)) { Text("Comenzar") }
        }
    }
}

