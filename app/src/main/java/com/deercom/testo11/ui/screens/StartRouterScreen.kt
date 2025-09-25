package com.deercom.testo11.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deercom.testo11.start.StartViewModel
import com.deercom.testo11.ui.screens.components.AppScaffold

@Composable
fun StartRouterScreen(
    goWizard: () -> Unit,
    goLogin: () -> Unit
) {
    val vm: StartViewModel = hiltViewModel()
    val companyName = vm.companyName.collectAsStateWithLifecycle().value
    val alias = vm.alias.collectAsStateWithLifecycle().value

    LaunchedEffect(companyName, alias) {
        if (companyName.isBlank() || alias.isBlank()) goWizard()
        else goLogin()
    }

    AppScaffold(
        topBar = { /* sin top bar en el splash/router */ }
    ) { _ ->
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
