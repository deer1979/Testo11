package com.deercom.testo11.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.deercom.testo11.data.User
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar
import com.deercom.testo11.ui.screens.users.UsersListScreen

@Composable
fun HomeWithBottomBarScreen(
    onEditUser: (User) -> Unit
) {
    var tab by remember { mutableStateOf(0) }
    val items = listOf("Usuarios", "Inicio", "Empresa", "Ajustes")
    val icons = listOf(Icons.Filled.Groups, Icons.Filled.Home, Icons.Filled.Business, Icons.Filled.Settings)

    AppScaffold(
        topBar = { AppTopBar(title = items[tab]) }
    ) { pad ->
        Scaffold(
            modifier = Modifier.padding(pad),
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = tab == index,
                            onClick = { tab = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        ) { inner ->
            Box(Modifier.padding(inner).fillMaxSize()) {
                when (tab) {
                    0 -> UsersListScreen(onEdit = onEditUser)
                    1 -> Placeholder("Inicio")
                    2 -> Placeholder("Empresa")
                    3 -> Placeholder("Ajustes")
                }
            }
        }
    }
}

@Composable
private fun Placeholder(text: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Sección: $text")
        Text("Contenido en construcción.")
    }
}

