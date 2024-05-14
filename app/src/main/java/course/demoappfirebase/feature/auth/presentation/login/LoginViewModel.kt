package course.demoappfirebase.feature.auth.presentation.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import course.demoappfirebase.core.untils.Resource
import course.demoappfirebase.feature.auth.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {


    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    private val _loginFlow = MutableStateFlow<Resource>(Resource.Init)
    var loginFlow: StateFlow<Resource> = _loginFlow

    init {
        if(FirebaseAuth.getInstance().currentUser != null){
            _loginFlow.value = Resource.Success
        }else{
            _loginFlow.value = Resource.Init
        }
    }


    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }

            LoginUIEvent.NavigateClicked -> TODO()
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun login() {

        _loginFlow.value = Resource.Loading
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginFlow.value = Resource.Success
                    Log.d(TAG, "Login successful")
                } else {
                    _loginFlow.value = Resource.Failure
                    Log.e(TAG, "Login failed: ${task.exception?.message}")
                }
            }

    }

}


