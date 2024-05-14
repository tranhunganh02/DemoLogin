package course.demoappfirebase.feature.auth.presentation.login

sealed class LoginUIEvent{

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()

    object NavigateClicked : LoginUIEvent()
}
