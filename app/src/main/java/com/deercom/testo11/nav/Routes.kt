package com.deercom.testo11.nav
sealed class Route(val path: String) {
    data object StartRouter     : Route("start/router")
    data object WizardStart     : Route("wizard/start")
    data object CreateCompany   : Route("wizard/create_company")
    data object CreateAdminUser : Route("wizard/create_admin_user")
    data object ProfileCard     : Route("profile/card")
    data object LoginAlias      : Route("auth/login_alias")
    data object Home            : Route("home")
}
