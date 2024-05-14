package course.demoappfirebase.feature.home.presentation

import course.demoappfirebase.feature.home.data.User
import course.demoappfirebase.feature.home.utils.Resources



data class HomeUIState(
    val dataUser: Resources<User> = Resources.Loading(),
    val noteDeletedStatus: Boolean = false,
)
