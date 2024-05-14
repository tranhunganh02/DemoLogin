package demo.firebase.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nativemobilebits.loginflow.data.signup.SignupViewModel
import course.demoappfirebase.feature.auth.presentation.login.LoginViewModel
import course.demoappfirebase.feature.auth.presentation.login.ui.LoginScreen
import course.demoappfirebase.feature.auth.presentation.signup.ui.SignupScreen
import course.demoappfirebase.feature.home.presentation.HomeViewModel
import course.demoappfirebase.feature.home.presentation.ui.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN,
    homeViewModel: HomeViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    signupViewModel: SignupViewModel = viewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
              LoginScreen(loginViewModel, navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(signupViewModel, navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(homeViewModel, navController)
        }
    }
}

