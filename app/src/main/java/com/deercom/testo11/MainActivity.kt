
package com.deercom.testo11
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { HelloScreen() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Testo11") }) }) { inner ->
        Box(Modifier.padding(inner).fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Hello Testo11")
        }
    }
}
