package course.demoappfirebase.feature.auth.presentation.login.ui
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import course.demoappfirebase.R
import course.demoappfirebase.core.untils.Resource
import course.demoappfirebase.feature.auth.presentation.component.AuthHeader
import course.demoappfirebase.feature.auth.presentation.component.MyTextFieldComponent
import course.demoappfirebase.feature.auth.presentation.component.PasswordTextFieldComponent
import course.demoappfirebase.feature.auth.presentation.login.LoginUIEvent
import course.demoappfirebase.feature.auth.presentation.login.LoginViewModel
import course.demoappfirebase.ui.theme.DemoAppFirebaseTheme
import demo.firebase.core.navigation.ROUTE_HOME
import demo.firebase.core.navigation.ROUTE_LOGIN
import demo.firebase.core.navigation.ROUTE_SIGNUP

@Composable
fun LoginScreen(viewModel: LoginViewModel?,  navController: NavController) {


    val loginFlow = viewModel?.loginFlow?.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        AuthHeader()
        AuthHeader()
        AuthHeader()


        Column {


            viewModel?.loginUIState?.value?.let { it ->
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = null,
                    onTextChanged = {
                        viewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = it.emailError
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            if (viewModel != null) {
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = null,
                    onTextSelected = {
                        viewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = viewModel.loginUIState.value.passwordError
                )
            }
        }

        viewModel?.allValidationsPassed?.let {
            Button(
                enabled = it.value,
                onClick = {
                    viewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },

                ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }


        Text(
            modifier = Modifier

                .clickable {
                    navController.navigate(ROUTE_SIGNUP) {

                    }
                },
            text = stringResource(id = R.string.dont_have_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        loginFlow?.value?.let {
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
                        navController.navigate(ROUTE_HOME)
                    }
                }
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreviewLight() {
    DemoAppFirebaseTheme {
        LoginScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewDark() {
    DemoAppFirebaseTheme {
        LoginScreen(null, rememberNavController())
    }
}