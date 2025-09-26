@@
-package com.deercom.testo11.ui.screens.onboarding
package com.deercom.testo11.ui.screens.onboarding
 
 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.height
 import androidx.compose.foundation.layout.padding
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.ArrowBack
-import androidx.compose.material3.Button
import androidx.compose.material3.Button
 import androidx.compose.material3.Card
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.OutlinedTextField
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.unit.dp
 import com.deercom.testo11.ui.screens.components.AppScaffold
 import com.deercom.testo11.ui.screens.components.AppTopBar
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import com.deercom.testo11.onboarding.OnboardingViewModel
 
-@Composable
-fun OnboardingScreen(onFinished: () -> Unit) {
@Composable
fun OnboardingScreen(
    vm: OnboardingViewModel,
    onGotoSummary: () -> Unit
) {
     AppScaffold(topBar = {
         AppTopBar(
             title = "Alta inicial",
             navigationIcon = Icons.Filled.ArrowBack,
             onNavigate = { /* back se maneja desde NavGraph */ },
             actions = { com.deercom.testo11.ui.screens.components.TopBarOverflow() }
         )
     }) { pad ->
-        Column(
        Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(pad)
                 .padding(16.dp),
             verticalArrangement = Arrangement.spacedBy(16.dp)
         ) {
-            Card(modifier = Modifier.fillMaxWidth()) {
-                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
-                    Text("Empresa", style = MaterialTheme.typography.titleMedium)
-                    val company = remember { mutableStateOf("") }
-                    val ruc = remember { mutableStateOf("") }
-                    OutlinedTextField(value = company.value, onValueChange = { company.value = it }, label = { Text("Nombre de empresa") })
-                    OutlinedTextField(value = ruc.value, onValueChange = { ruc.value = it }, label = { Text("RUC/ID") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
-                }
-            }
-            Card(modifier = Modifier.fillMaxWidth()) {
-                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
-                    Text("Perfil", style = MaterialTheme.typography.titleMedium)
-                    val first = remember { mutableStateOf("") }
-                    val last = remember { mutableStateOf("") }
-                    val phone = remember { mutableStateOf("") }
-                    OutlinedTextField(value = first.value, onValueChange = { first.value = it }, label = { Text("Nombres") }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))
-                    OutlinedTextField(value = last.value, onValueChange = { last.value = it }, label = { Text("Apellidos") })
-                    OutlinedTextField(value = phone.value, onValueChange = { phone.value = it }, label = { Text("Teléfono") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
-                }
-            }
-            Card(modifier = Modifier.fillMaxWidth()) {
-                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
-                    Text("Seguridad", style = MaterialTheme.typography.titleMedium)
-                    val user = remember { mutableStateOf("") }
-                    val pass = remember { mutableStateOf("") }
-                    OutlinedTextField(value = user.value, onValueChange = { user.value = it }, label = { Text("Usuario/Alias") }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))
-                    OutlinedTextField(value = pass.value, onValueChange = { pass.value = it }, label = { Text("Contraseña") })
-                }
-            }
-            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
-                Button(onClick = onFinished) { Text("Guardar y finalizar") }
-            }
            when (vm.current) {
                com.deercom.testo11.onboarding.OnboardingViewModel.Section.EMPRESA -> EmpresaStep(vm)
                com.deercom.testo11.onboarding.OnboardingViewModel.Section.PERFIL -> PerfilStep(vm)
                com.deercom.testo11.onboarding.OnboardingViewModel.Section.SEGURIDAD -> SeguridadStep(vm)
                com.deercom.testo11.onboarding.OnboardingViewModel.Section.FOTO -> FotoStep(vm)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { vm.back() }) { Text("Atrás") }
                Spacer(Modifier.weight(1f))
                if (vm.current == com.deercom.testo11.onboarding.OnboardingViewModel.Section.FOTO) {
                    Button(onClick = onGotoSummary) { Text("Continuar a resumen") }
                } else {
                    Button(onClick = { vm.next() }) { Text("Siguiente") }
                }
            }
         }
     }
 }
 
@Composable
private fun EmpresaStep(vm: OnboardingViewModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Empresa", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = vm.companyName, onValueChange = { vm.companyName = it },
                label = { Text("Nombre de empresa") })
            OutlinedTextField(
                value = vm.companyId, onValueChange = { vm.companyId = it },
                label = { Text("RUC/ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun PerfilStep(vm: OnboardingViewModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Perfil", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = vm.firstName, onValueChange = { vm.firstName = it },
                label = { Text("Nombres") }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = vm.lastName, onValueChange = { vm.lastName = it },
                label = { Text("Apellidos") })
            OutlinedTextField(
                value = vm.phone, onValueChange = { vm.phone = it },
                label = { Text("Teléfono") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
        }
    }
}

@Composable
private fun SeguridadStep(vm: OnboardingViewModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Seguridad", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = vm.username, onValueChange = { vm.username = it },
                label = { Text("Usuario/Alias") }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = vm.password, onValueChange = { vm.password = it },
                label = { Text("Contraseña") }
            )
        }
    }
}

@Composable
private fun FotoStep(vm: OnboardingViewModel) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        vm.photoUri = uri?.toString()
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Foto", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { launcher.launch("image/*") }) { Text("Seleccionar foto") }
            vm.photoUri?.let { src ->
                Image(
                    painter = rememberAsyncImagePainter(model = src),
                    contentDescription = "preview",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
