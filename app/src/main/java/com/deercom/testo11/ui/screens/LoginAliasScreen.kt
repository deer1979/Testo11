package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deercom.testo11.auth.AuthViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar
import kotlinx.coroutines.launch

@Composable
fun LoginAliasScreen(
    onSuccess: () -> Unit,
    onBack: () -> Unit
) {
    val vm = hiltViewModel<AuthViewModel>()
    val savedAlias by vm.aliasFlow.collectAsStateWithLifecycle(initialValue = "")
    var alias by remember { mutableStateOf(TextFieldValue("")) }
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Prefill una vez cuando exista alias guardado
    LaunchedEffect(savedAlias) {
        if (savedAlias.isNotBlank() && alias.text.isBlank()) {
            alias = TextFieldValue(savedAlias)
        }
    }

    AppScaffold(
        topBar = { AppTopBar(title = "Iniciar sesión", navigationIcon = Icons.AutoMirrored.Filled.ArrowBack, onNavigate = onBack) }
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

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(
                    onClick = {
                        scope.launch {
                            val ok = vm.isAliasValid(alias.text.trim())
                            if (ok) onSuccess()
                            else snackbar.showSnackbar("Alias incorrecto. Usa exactamente el alias creado en el wizard.")
                        }
                    },
                    enabled = alias.text.isNotBlank()
                ) { Text("Entrar") }
            }
        }
    }
}
