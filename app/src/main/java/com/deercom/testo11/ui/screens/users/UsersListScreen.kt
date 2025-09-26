package com.deercom.testo11.ui.screens.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deercom.testo11.data.User
import com.deercom.testo11.users.UsersViewModel

@Composable
fun UsersListScreen(
    onEdit: (User) -> Unit
) {
    val vm: UsersViewModel = hiltViewModel()
    val users by vm.users.collectAsState()

    var selected by remember { mutableStateOf<User?>(null) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ListItem(
            leadingContent = { Icon(Icons.Filled.Groups, null) },
            headlineContent = { Text("Usuarios (${users.size})") },
            supportingContent = { Text("Toque un usuario para ver detalle") }
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()) {
            items(users) { u ->
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().clickable { selected = u }
                ) {
                    ListItem(
                        headlineContent = { Text("${u.nombres} ${u.apellidos}".trim()) },
                        supportingContent = { Text("${u.alias} • ${u.cargo.ifBlank { "Sin cargo" }}") }
                    )
                }
            }
        }
    }

    if (selected != null) {
        val u = selected!!
        AlertDialog(
            onDismissRequest = { selected = null },
            confirmButton = {
                TextButton(onClick = { onEdit(u); selected = null }) {
                    Icon(Icons.Filled.Edit, null); Spacer(Modifier.width(6.dp)); Text("Editar")
                }
            },
            dismissButton = { TextButton(onClick = { selected = null }) { Text("Cerrar") } },
            title = { Text("${u.nombres} ${u.apellidos}".trim()) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Usuario: ${u.alias}")
                    Text("Cargo: ${u.cargo.ifBlank { "—" }}")
                    Text("Documento: ${u.documento.ifBlank { "—" }}")
                    Text("Tel. personal: ${u.telefonoPersonal.ifBlank { "—" }}")
                    Text("Tel. dispositivo: ${u.telefonoDispositivo.ifBlank { "—" }}")
                    Text("Localidad: ${u.localidad.ifBlank { "—" }}")
                }
            }
        )
    }
}

