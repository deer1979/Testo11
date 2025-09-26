@@
 package com.deercom.testo11.nav
 
 import androidx.compose.runtime.Composable
 import androidx.navigation.compose.NavHost
 import androidx.navigation.compose.composable
 import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.first
import com.deercom.testo11.onboarding.OnboardingViewModel
import com.deercom.testo11.ui.screens.onboarding.OnboardingScreen
import com.deercom.testo11.login.LoginUserPassScreen
import com.deercom.testo11.ui.screens.home.HomeScreen as HomeWithBottomBarScreen /* ajusta si tu Home se llama distinto */
 
 @Composable
 fun NewAppNavGraph() {
     val nav = rememberNavController()
-    NavHost(navController = nav, startDestination = NewRoutes.Onboarding.route) {
    // PR #1: Iniciamos en Start y redirigimos según setup_done
    NavHost(navController = nav, startDestination = NewRoutes.Start.route) {
        composable(NewRoutes.Start.route) {
            val vm: OnboardingViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                val done = vm.setupDoneFlow().first()
                if (done) {
                    nav.navigate(NewRoutes.Login.route) { popUpTo(0) }
                } else {
                    nav.navigate(NewRoutes.Onboarding.route) { popUpTo(0) }
                }
            }
        }
         composable(NewRoutes.Onboarding.route) {
-            com.deercom.testo11.ui.screens.onboarding.OnboardingScreen(
-                onFinished = { nav.navigate(NewRoutes.Login.route) { popUpTo(0) } }
-            )
            val vm: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                onFinished = {
                    vm.markSetupDone()
                    nav.navigate(NewRoutes.Login.route) { popUpTo(0) }
                }
            )
         }
         composable(NewRoutes.Login.route) {
-            com.deercom.testo11.login.LoginUserPassScreen(
            LoginUserPassScreen(
                 onLoginOk = { nav.navigate(NewRoutes.Home.route) { popUpTo(0) } }
             )
         }
         composable(NewRoutes.Home.route) {
-            com.deercom.testo11.ui.screens.home.HomeWithBottomBarScreen(
            HomeWithBottomBarScreen(
                 onEditUser = { /* futuro */ }
             )
         }
     }
 }
