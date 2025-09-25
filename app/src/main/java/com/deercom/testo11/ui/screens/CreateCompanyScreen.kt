package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.wizard.WizardViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCompanyScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val vm: WizardViewModel = hiltViewModel()
    var name by remember { mutableStateOf(TextFieldValue("")) }
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Crear empresa") }) },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { pad ->
        Column(
            Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre de la empresa") },
                leadingIcon = { Icon(Icons.Filled.Domain, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(
                    onClick = {
                        val text = name.text.trim()
                        if (text.isEmpty()) {
                            scope.launch { snackbar.showSnackbar("Ingresa un nombre válido") }
                        } else {
                            vm.saveCompany(text) {
                                onNext()
                            }
                        }
                    },
                    enabled = name.text.isNotBlank()
                ) { Text("Continuar") }
            }
        }
    }
}
