package com.deercom.testo11.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.wizard.WizardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCompanyScreen(onNext: () -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    Scaffold(
        topBar = { TopAppBar(title = { Text("Crear empresa") }) }
    ) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Nombre de la empresa") },
                leadingIcon = { Icon(Icons.Default.Domain, null) }, singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(onClick = onNext, enabled = name.text.isNotBlank()) { Text("Continuar") }
            }
        }
    }

    @Composable
    fun CreateCompanyScreen(onNext: () -> Unit, onBack: () -> Unit) {
        val vm = hiltViewModel<WizardViewModel>()
        // ...
        Button(onClick = { vm.saveCompany(name.text) { onNext() } }, enabled = name.text.isNotBlank()) {
            Text("Continuar")
        }
    }
}


