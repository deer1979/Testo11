package com.deercom.testo11.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.onboarding.OnboardingViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar

@Composable
fun OnboardingScreen(
    onFinishFirstTime: () -> Unit,
    onFinishFromHome: () -> Unit
) {
    val vm: OnboardingViewModel = hiltViewModel()
    val s by vm.state.collectAsState()

    var tab by remember { mutableStateOf(0) }
    val tabs = listOf("Empresa", "Perfil", "Seguridad", "Foto")

    AppScaffold(
        topBar = {
            AppTopBar(
                title = "Alta inicial",
                navigationIcon = Icons.Filled.ArrowBack,
                onNavigate = { /* back se maneja desde NavGraph */ }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScrollableTabRow(selectedTabIndex = tab) {
                tabs.forEachIndexed { i, title ->
                    Tab(
                        selected = tab == i,
                        onClick = { tab = i },
                        text = { Text(title) }
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .widthIn(max = 480.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    when (tab) {
                        0 -> EmpresaTab(s, vm)
                        1 -> PerfilTab(s, vm)
                        2 -> SeguridadTab(s, vm)
                        3 -> FotoTab(s, vm)
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { vm.save(onSaved = onFinishFromHome) },
                            modifier = Modifier.weight(1f)
                        ) { Text("Guardar borrador") }
                        Button(
                            onClick = { vm.save(onSaved = onFinishFirstTime) },
                            modifier = Modifier.weight(1f)
                        ) { Text("Guardar y finalizar") }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmpresaTab(s: OnboardingViewModel.UiState, vm: OnboardingViewModel) {
    OutlinedTextField(
        value = s.empresaNombre,
        onValueChange = { new -> vm.update { it.copy(empresaNombre = new) } },
        label = { Text("Nombre de la empresa") },
        leadingIcon = { Icon(Icons.Filled.Business, null) },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.ruc,
        onValueChange = { new -> vm.update { it.copy(ruc = new) } },
        label = { Text("RUC / NIT / ID fiscal") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.localidad,
        onValueChange = { new -> vm.update { it.copy(localidad = new) } },
        label = { Text("Localidad (código)") },
        modifier = Modifier.fillMaxWidth()
    )
    AssistChip(onClick = { vm.regenerateAlias() }, label = { Text("Sugerir alias con localidad") })
}

@Composable
private fun PerfilTab(s: OnboardingViewModel.UiState, vm: OnboardingViewModel) {
    OutlinedTextField(
        value = s.alias,
        onValueChange = { new -> vm.update { it.copy(alias = new) } },
        label = { Text("Usuario (alias)") },
        leadingIcon = { Icon(Icons.Filled.Person, null) },
        modifier = Modifier.fillMaxWidth()
    )
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = s.nombres,
            onValueChange = { new -> vm.update { it.copy(nombres = new) } },
            label = { Text("Nombres") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        OutlinedTextField(
            value = s.apellidos,
            onValueChange = { new -> vm.update { it.copy(apellidos = new) } },
            label = { Text("Apellidos") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
    OutlinedTextField(
        value = s.cargo,
        onValueChange = { new -> vm.update { it.copy(cargo = new) } },
        label = { Text("Cargo") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.documento,
        onValueChange = { new -> vm.update { it.copy(documento = new) } },
        label = { Text("Documento (ID)") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.telefonoPersonal,
        onValueChange = { new -> vm.update { it.copy(telefonoPersonal = new) } },
        label = { Text("Teléfono personal") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.telefonoDispositivo,
        onValueChange = { new -> vm.update { it.copy(telefonoDispositivo = new) } },
        label = { Text("Teléfono del dispositivo") },
        trailingIcon = {
            AssistChip(onClick = { /* Placeholder: Phone Number Hint en implementación */ }, label = { Text("Sugerir número") })
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun SeguridadTab(s: OnboardingViewModel.UiState, vm: OnboardingViewModel) {
    var show1 by remember { mutableStateOf(false) }
    var show2 by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = s.password,
        onValueChange = { new -> vm.update { it.copy(password = new) } },
        label = { Text("Contraseña") },
        visualTransformation = if (show1) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = { show1 = !show1 }) { Text(if (show1) "Ocultar" else "Ver") }
        },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = s.passwordConfirm,
        onValueChange = { new -> vm.update { it.copy(passwordConfirm = new) } },
        label = { Text("Confirmar contraseña") },
        visualTransformation = if (show2) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = { show2 = !show2 }) { Text(if (show2) "Ocultar" else "Ver") }
        },
        modifier = Modifier.fillMaxWidth()
    )
    // Indicador simple (informativo)
    LinearProgressIndicator(progress = { (s.password.length.coerceAtMost(12)) / 12f })
}

@Composable
private fun FotoTab(s: OnboardingViewModel.UiState, vm: OnboardingViewModel) {
    OutlinedTextField(
        value = s.fotoUri,
        onValueChange = { new -> vm.update { it.copy(fotoUri = new) } },
        label = { Text("Foto (URI)") },
        leadingIcon = { Icon(Icons.Filled.PhotoCamera, null) },
        modifier = Modifier.fillMaxWidth()
    )
    Text("En producción: tomar foto / galería + recorte y compresión.")
}

