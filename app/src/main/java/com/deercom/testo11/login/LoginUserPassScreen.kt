package com.deercom.testo11.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar

@Composable
fun LoginUserPassScreen(
    onSuccess: () -> Unit
) {
    var alias by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    AppScaffold(
        topBar = { AppTopBar(title = "Iniciar sesión") }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = alias, onValueChange = { alias = it },
                label = { Text("Usuario") },
                leadingIcon = { Icon(Icons.AutoMirrored.Filled.Login, null) },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = pass, onValueChange = { pass = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { onSuccess() }, // modo pruebas
                enabled = alias.isNotBlank()
            ) { Text("Entrar") }
        }
    }
}

