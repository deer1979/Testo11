package com.deercom.testo11.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deercom.testo11.login.LoginUserPassScreen
import com.deercom.testo11.ui.screens.home.HomeWithBottomBarScreen
import com.deercom.testo11.ui.screens.onboarding.OnboardingScreen

@Composable
fun NewAppNavGraph() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = NewRoutes.ONBOARDING) {
        // Alta unificada
        composable(NewRoutes.ONBOARDING) {
            OnboardingScreen(
                onFinishFirstTime = {
                    nav.navigate(NewRoutes.LOGIN_USER_PASS) {
                        popUpTo(NewRoutes.ONBOARDING) { inclusive = true }
                    }
                },
                onFinishFromHome = { nav.popBackStack() }
            )
        }
        // Login usuario + contraseña (modo pruebas)
        composable(NewRoutes.LOGIN_USER_PASS) {
            LoginUserPassScreen(
                onSuccess = {
                    nav.navigate(NewRoutes.HOME) {
                        popUpTo(NewRoutes.LOGIN_USER_PASS) { inclusive = true }
                    }
                },
                onBack = { nav.popBackStack() }
            )
        }
        // Home con bottom bar (Usuarios por defecto)
        composable(NewRoutes.HOME) {
            HomeWithBottomBarScreen(
                onEditUser = {
                    // Abrir Onboarding para "editar" (modo pruebas: reusamos la misma pantalla)
                    nav.navigate(NewRoutes.ONBOARDING)
                }
            )
        }
    }
}

