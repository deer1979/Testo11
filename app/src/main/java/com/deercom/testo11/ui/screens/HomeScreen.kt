package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deercom.testo11.home.HomeViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold
import com.deercom.testo11.ui.screens.components.AppTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val vm: HomeViewModel = hiltViewModel()
    val companyName = vm.companyName.collectAsStateWithLifecycle().value
    val alias = vm.alias.collectAsStateWithLifecycle().value

    AppScaffold(
        topBar = { AppTopBar(title = "Home") }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ListItem(
                headlineContent = { Text(companyName.ifBlank { "Empresa" }) },
                supportingContent = { Text(alias.ifBlank { "Sin alias de sesión" }) },
                leadingContent = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = { vm.logout(onLogout) }) { Text("Cerrar sesión") }
        }
    }
}
