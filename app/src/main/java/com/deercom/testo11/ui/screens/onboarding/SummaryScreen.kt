package com.deercom.testo11.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.deercom.testo11.onboarding.OnboardingViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar

@Composable
fun SummaryScreen(
    vm: OnboardingViewModel,
    onEditSection: (OnboardingViewModel.Section) -> Unit,
    onConfirm: () -> Unit
) {
    AppScaffold(topBar = {
        AppTopBar(title = "Resumen", actions = { com.deercom.testo11.ui.screens.components.TopBarOverflow() })
    }) { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Empresa", style = MaterialTheme.typography.titleMedium)
                    Text("Nombre: ${vm.companyName}")
                    Text("RUC/ID: ${vm.companyId}")
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { onEditSection(OnboardingViewModel.Section.EMPRESA) }) { Text("Editar") }
                    }
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Perfil", style = MaterialTheme.typography.titleMedium)
                    Text("Nombre: ${vm.firstName} ${vm.lastName}")
                    Text("Teléfono: ${vm.phone}")
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { onEditSection(OnboardingViewModel.Section.PERFIL) }) { Text("Editar") }
                    }
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Seguridad", style = MaterialTheme.typography.titleMedium)
                    Text("Usuario: ${vm.username}")
                    Row(horizontalArrangement = Arrangement.spacedBy(8	dp)) {
                        Button(onClick = { onEditSection(OnboardingViewModel.Section.SEGURIDAD) }) { Text("Editar") }
                    }
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Foto", style = MaterialTheme.typography.titleMedium)
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
                    Row(horizontalArrangement = Arrangement.spacedBy(8	dp)) {
                        Button(onClick = { onEditSection(OnboardingViewModel.Section.FOTO) }) { Text("Editar") }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12	dp)) {
                Button(onClick = { vm.saveAll(onConfirm) }) { Text("Confirmar y guardar") }
            }
        }
    }
}

