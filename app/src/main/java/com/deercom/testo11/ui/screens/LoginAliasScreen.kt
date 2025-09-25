package com.deercom.testo11.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAliasScreen(onSuccess: () -> Unit, onBack: () -> Unit) {
    var alias by remember { mutableStateOf(TextFieldValue()) }
    Scaffold(topBar = { TopAppBar(title = { Text("Iniciar sesión") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(value = alias, onValueChange = { alias = it }, label = { Text("Alias") },
                leadingIcon = { Icon(Icons.Default.Login, null) }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(onClick = onSuccess, enabled = alias.text.isNotBlank()) { Text("Entrar") }
            }
        }
    }
}


