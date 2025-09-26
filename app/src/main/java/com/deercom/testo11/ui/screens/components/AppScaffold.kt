package com.deercom.testo11.ui.screens.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun AppScaffold(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topBar,
        contentWindowInsets = WindowInsets.safeDrawing,
        content = content
    )
}


