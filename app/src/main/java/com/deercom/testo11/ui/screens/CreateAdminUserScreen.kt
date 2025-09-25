package com.deercom.testo11.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAdminUserScreen(onNext: () -> Unit, onBack: () -> Unit) {
    var alias by remember { mutableStateOf(TextFieldValue()) }
    Scaffold(topBar = { TopAppBar(title = { Text("Crear Admin Master") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(
                value = alias, onValueChange = { alias = it },
                label = { Text("Alias (sin correo)") },
                leadingIcon = { Icon(Icons.Default.AdminPanelSettings, null) }, singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(onClick = onNext, enabled = alias.text.length >= 3) { Text("Guardar y continuar") }
            }
        }
    }
}


