package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deercom.testo11.auth.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAliasScreen(
    onSuccess: () -> Unit,
    onBack: () -> Unit
) {
    val vm = hiltViewModel<AuthViewModel>()
    // Leemos el alias guardado en DataStore
    val savedAlias by vm.aliasFlow.collectAsStateWithLifecycle(initialValue = "")
    var alias by remember { mutableStateOf(TextFieldValue("")) }
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Prefill una vez al tener valor
    LaunchedEffect(savedAlias) {
        if (savedAlias.isNotBlank() && alias.text.isBlank()) {
            alias = TextFieldValue(savedAlias)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Iniciar sesión") }) },
        snackbarHost = { SnackbarHost(hostState = snackbar) }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = alias,
                onValueChange = { alias = it },
                label = { Text("Alias") },
                leadingIcon = { Icon(Icons.AutoMirrored.Filled.Login, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // 🔎 Línea de ayuda temporal para depurar
            AssistChip(
                onClick = {},
                label = { Text(if (savedAlias.isBlank()) "Alias guardado: (vacío)" else "Alias guardado: $savedAlias") }
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(
                    onClick = {
                        scope.launch {
                            val input = alias.text.trim()
                            val ok = vm.isAliasValid(input)
                            if (ok) {
                                onSuccess()
                            } else {
                                // Si no hay alias guardado, mensaje específico
                                val msg = if (savedAlias.isBlank())
                                    "No hay alias guardado. Completa el wizard primero."
                                else
                                    "Alias incorrecto. Usa exactamente el alias creado en el wizard."
                                snackbar.showSnackbar(msg)
                            }
                        }
                    },
                    enabled = alias.text.isNotBlank()
                ) { Text("Entrar") }
            }
        }
    }
}
