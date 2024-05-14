package course.demoappfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nativemobilebits.loginflow.data.signup.SignupViewModel
import course.demoappfirebase.feature.auth.presentation.login.LoginViewModel
import course.demoappfirebase.feature.home.presentation.HomeViewModel
import course.demoappfirebase.ui.theme.DemoAppFirebaseTheme
import demo.firebase.core.navigation.AppNavHost

class MainActivity : ComponentActivity() {


        private val homeViewModel by viewModels<HomeViewModel>()
        private val loginViewModel by viewModels<LoginViewModel>()
        private val signupViewModel by viewModels<SignupViewModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                DemoAppFirebaseTheme {

                        AppNavHost(
                            homeViewModel = homeViewModel,
                            loginViewModel = loginViewModel,
                            signupViewModel = signupViewModel
                        )

                }
            }
        }


}
