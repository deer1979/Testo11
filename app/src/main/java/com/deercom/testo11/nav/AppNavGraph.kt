package com.deercom.testo11.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deercom.testo11.ui.screens.*

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Route.StartRouter.path) {
        composable(Route.StartRouter.path) {
            StartRouterScreen(
                goWizard = { nav.navigate(Route.WizardStart.path) { popUpTo(0) } },
                goLogin  = { nav.navigate(Route.LoginAlias.path) { popUpTo(0) } }
            )
        }
        composable(Route.WizardStart.path)     { WizardStartScreen(onNext = { nav.navigate(Route.CreateCompany.path) }) }
        composable(Route.CreateCompany.path)   { CreateCompanyScreen(onNext = { nav.navigate(Route.CreateAdminUser.path) }, onBack = { nav.popBackStack() }) }
        composable(Route.CreateAdminUser.path) { CreateAdminUserScreen(onNext = { nav.navigate(Route.ProfileCard.path) }, onBack = { nav.popBackStack() }) }
        composable(Route.ProfileCard.path)     { ProfileCardScreen(onNext = { nav.navigate(Route.LoginAlias.path) }, onBack = { nav.popBackStack() }) }
        composable(Route.LoginAlias.path)      { LoginAliasScreen(onSuccess = { nav.navigate(Route.Home.path) }, onBack = { nav.popBackStack() }) }
        composable(Route.Home.path)            { HomeScreen(onLogout = { nav.navigate(Route.LoginAlias.path) { popUpTo(0) } }) }
    }
}
