package course.demoappfirebase.feature.auth.presentation.signup

data class SignupUIState(
    var firstName :String = "",
    var lastName  :String = "",
    var email  :String = "",
    var password  :String = "",
    var privacyPolicyAccepted :Boolean = false,


    var firstNameError :Boolean = false,
    var lastNameError : Boolean = false,
    var emailError :Boolean = false,
    var passwordError : Boolean = false,
    var privacyPolicyError:Boolean = false
)
