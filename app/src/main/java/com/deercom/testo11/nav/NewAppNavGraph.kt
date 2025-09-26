@@
 package com.deercom.testo11.nav
 
 import androidx.compose.runtime.Composable
 import androidx.navigation.compose.NavHost
 import androidx.navigation.compose.composable
 import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.LaunchedEffect
import com.deercom.testo11.ui.screens.onboarding.OnboardingScreen
import com.deercom.testo11.ui.screens.onboarding.SummaryScreen
import com.deercom.testo11.login.LoginUserPassScreen
import com.deercom.testo11.ui.screens.home.HomeWithBottomBarScreen
import com.deercom.testo11.users.UsersViewModel
import com.deercom.testo11.onboarding.OnboardingViewModel
import com.deercom.testo11.data.LocalUserRepository
 
 @Composable
 fun NewAppNavGraph() {
     val nav = rememberNavController()
-    NavHost(navController = nav, startDestination = NewRoutes.Onboarding.route) {
-        composable(NewRoutes.Onboarding.route) {
-            com.deercom.testo11.ui.screens.onboarding.OnboardingScreen(
-                onFinished = { nav.navigate(NewRoutes.Login.route) { popUpTo(0) } }
-            )
-        }
-        composable(NewRoutes.Login.route) {
-            com.deercom.testo11.login.LoginUserPassScreen(
-                onLoginOk = { nav.navigate(NewRoutes.Home.route) { popUpTo(0) } }
-            )
-        }
-        composable(NewRoutes.Home.route) {
-            com.deercom.testo11.ui.screens.home.HomeWithBottomBarScreen(
-                onEditUser = { /* future */ }
-            )
-        }
-    }
    NavHost(navController = nav, startDestination = NewRoutes.StartRouter.route) {
        composable(NewRoutes.StartRouter.route) {
            val repo: LocalUserRepository = hiltViewModel<UsersViewModel>().repo
            LaunchedEffect(Unit) {
                val setupDone = repo.isSetupDone().first()
                val hasUsers = repo.hasAnyUser().first()
                if (setupDone && hasUsers) {
                    nav.navigate(NewRoutes.Login.route) { popUpTo(0) }
                } else {
                    nav.navigate(NewRoutes.Onboarding.route) { popUpTo(0) }
                }
            }
        }
        composable(NewRoutes.Onboarding.route) {
            val vm: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                vm = vm,
                onGotoSummary = { nav.navigate(NewRoutes.Summary.route) }
            )
        }
        composable(NewRoutes.Summary.route) {
            val vm: OnboardingViewModel = hiltViewModel()
            SummaryScreen(
                vm = vm,
                onEditSection = { section ->
                    vm.goToSection(section)
                    nav.popBackStack()
                },
                onConfirm = {
                    nav.navigate(NewRoutes.Login.route) { popUpTo(0) }
                }
            )
        }
        composable(NewRoutes.Login.route) {
            LoginUserPassScreen(
                onLoginOk = { nav.navigate(NewRoutes.Home.route) { popUpTo(0) } }
            )
        }
        composable(NewRoutes.Home.route) {
            HomeWithBottomBarScreen(
                onEditUser = { /* future */ }
            )
        }
    }
 }
