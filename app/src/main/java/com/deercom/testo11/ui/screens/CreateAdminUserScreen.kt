package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.wizard.WizardViewModel
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAdminUserScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val vm: WizardViewModel = hiltViewModel()
    var alias by remember { mutableStateOf(TextFieldValue("")) }

    androidx.compose.material3.Scaffold(
        topBar = { TopAppBar(title = { Text("Crear Admin Master") }) }
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
                            onError = { /* aquí podrías mostrar un Snackbar si quieres */ }
                        )
                    },
                    enabled = alias.text.trim().length >= 3
                ) {
                    Text("Guardar y continuar")
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Usa un alias de 3 a 20 caracteres (letras, números, _ o .).",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
