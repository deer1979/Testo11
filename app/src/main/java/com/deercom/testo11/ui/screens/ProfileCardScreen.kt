package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.profile.ProfileViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCardScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val vm: ProfileViewModel = hiltViewModel()

    var name  by remember { mutableStateOf(TextFieldValue(vm.name.value)) }
    var phone by remember { mutableStateOf(TextFieldValue(vm.phone.value)) }
    var docId by remember { mutableStateOf(TextFieldValue(vm.docId.value)) }

    AppScaffold(
        topBar = { AppTopBar(title = "Perfil", navigationIcon = Icons.AutoMirrored.Filled.ArrowBack, onNavigate = onBack) }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Filled.Badge, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = docId,
                onValueChange = { docId = it },
                label = { Text("Documento") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Atrás") }
                Button(
                    onClick = { vm.save(name.text, phone.text, docId.text, onNext) },
                    enabled = name.text.isNotBlank()
                ) { Text("Guardar y continuar") }
            }
        }
    }
}
