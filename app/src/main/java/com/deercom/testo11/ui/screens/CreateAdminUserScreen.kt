package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar
import com.deercom.testo11.wizard.WizardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAdminUserScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val vm: WizardViewModel = hiltViewModel()
    var alias by remember { mutableStateOf(TextFieldValue("")) }

    AppScaffold(
        topBar = { AppTopBar(title = "Crear Admin Master", navigationIcon = Icons.AutoMirrored.Filled.ArrowBack, onNavigate = onBack) }
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
                label = { Text("Alias (sin correo)") },
                leadingIcon = { Icon(Icons.Filled.AdminPanelSettings, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(
                    onClick = {
                        vm.saveAdminAlias(
                            alias = alias.text,
                            onSaved = onNext,
                            onError = { /* podrías mostrar un Snackbar si quieres */ }
                        )
                    },
                    enabled = alias.text.trim().length >= 3
                ) { Text("Guardar y continuar") }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Usa un alias de 3 a 20 caracteres (letras, números, _ o .).",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
