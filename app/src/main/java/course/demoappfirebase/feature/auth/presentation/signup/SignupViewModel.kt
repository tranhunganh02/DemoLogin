package com.nativemobilebits.loginflow.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.UserProfileChangeRequest
import course.demoappfirebase.core.untils.Resource
import course.demoappfirebase.feature.auth.presentation.signup.SignupUIState
import course.demoappfirebase.feature.auth.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SignupViewModel : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName


    var registrationUIState = mutableStateOf(SignupUIState())

    var allValidationsPassed = mutableStateOf(false)


    private val _signupFLow = MutableStateFlow<Resource>(Resource.Init)
    var signupFLow: StateFlow<Resource> = _signupFLow

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            first_name = registrationUIState.value.firstName,
            last_name = registrationUIState.value.lastName
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String, first_name: String, last_name: String) {

        _signupFLow.value = Resource.Loading

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                if (it.isSuccessful) {
                    val user = it.result?.user

                    // Update the user's profile with the last name and first name
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName("$first_name $last_name") // Replace with the desired last name and first name
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User profile updated successfully")
                                _signupFLow.value = Resource.Success
                            } else {
                                Log.e(TAG, "Error updating user profile", task.exception)
                                _signupFLow.value = Resource.Failure
                            }
                        }
                } else {
                    _signupFLow.value = Resource.Failure
                }
            }

            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }

    }


}
