package course.demoappfirebase.feature.home.presentation.ui

import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import course.demoappfirebase.R
import course.demoappfirebase.feature.home.presentation.HomeViewModel
import course.demoappfirebase.feature.home.presentation.HomeUIState
import course.demoappfirebase.feature.home.utils.Resources
import course.demoappfirebase.ui.theme.DemoAppFirebaseTheme
import demo.firebase.core.navigation.ROUTE_HOME


@Composable
fun HomeScreen(viewModel: HomeViewModel?, navController: NavHostController) {
    val homeUiState = viewModel?.homeUiState ?: HomeUIState()
    LaunchedEffect(key1 = Unit) {
        viewModel?.fetchCurrentUser()
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when(homeUiState.dataUser) {
            is Resources.Loading -> {
               CircularProgressIndicator(
                   modifier = Modifier
                       .fillMaxSize()
                       .wrapContentSize(align = Alignment.Center)
               )

           }
            is Resources.Success -> {
                Text(
                    text = stringResource(id = R.string.welcome_back),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = homeUiState.dataUser.data?.displayName ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = stringResource(id = R.string.name),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(0.3f),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = homeUiState.dataUser.data?.displayName ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(0.7f),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = stringResource(id = R.string.email),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(0.3f),
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = homeUiState.dataUser.data?.email ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(0.7f),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Button(
                        onClick = {
                            viewModel?.logout()
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 15.dp)
                    ) {
                        Text(text = stringResource(id = R.string.logout))
                    }
                }
            }
            is Resources.Error -> {
                Text(
                    text = "Unknown Error",
                    color = Color.Red
                )
            }
            else -> {}
        }
        LaunchedEffect(key1 = viewModel?.hasUser) {
            if (viewModel?.hasUser?.value == false) {
                navController.popBackStack(ROUTE_HOME, inclusive = true) // Chuyển hướng người dùng đến màn hình đăng nhập
            }
        }

    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    DemoAppFirebaseTheme {
        HomeScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    DemoAppFirebaseTheme {
        HomeScreen(null, rememberNavController())
    }
}