package course.demoappfirebase.feature.auth.presentation.signup.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nativemobilebits.loginflow.data.signup.SignupUIEvent
import com.nativemobilebits.loginflow.data.signup.SignupViewModel
import course.demoappfirebase.R
import course.demoappfirebase.core.untils.Resource
import course.demoappfirebase.feature.auth.presentation.component.AuthHeader
import course.demoappfirebase.feature.auth.presentation.component.MyTextFieldComponent
import course.demoappfirebase.feature.auth.presentation.component.PasswordTextFieldComponent
import course.demoappfirebase.feature.auth.presentation.login.LoginUIEvent
import course.demoappfirebase.feature.auth.presentation.login.LoginViewModel
import demo.firebase.core.navigation.ROUTE_HOME
import demo.firebase.core.navigation.ROUTE_LOGIN
import demo.firebase.core.navigation.ROUTE_SIGNUP

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SignupScreen(viewModel: SignupViewModel?, navController: NavController) {


    val signupFLow = viewModel?.signupFLow?.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        AuthHeader()


        Column {

            viewModel?.signupFLow?.value?.let {
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource = null,
                    onTextChanged = {
                        viewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },

                    )
            }
            Spacer(modifier = Modifier.height(50.dp))

            viewModel?.signupFLow?.value?.let {
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = null,
                    onTextChanged = {
                        viewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },

                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            viewModel?.signupFLow?.value?.let {
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = null,
                    onTextChanged = {
                        viewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },

                    )
            }
            Spacer(modifier = Modifier.height(50.dp))

            if (viewModel != null) {
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = null,
                    onTextSelected = {
                        viewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },

                )
            }
        }

        Button(
            onClick = {
                viewModel?.onEvent(SignupUIEvent.RegisterButtonClicked)
            },

            ) {
            Text(
                text = stringResource(id = R.string.signup),
                style = MaterialTheme.typography.titleMedium
            )
        }


        Text(
            modifier = Modifier

                .clickable {
                    navController.navigate(ROUTE_LOGIN) {

                    }
                },
            text = "Already have an account?",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        signupFLow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, "login fail", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier)
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_LOGIN)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}