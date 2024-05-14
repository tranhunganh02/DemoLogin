package course.demoappfirebase.feature.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import course.demoappfirebase.feature.home.data.User
import course.demoappfirebase.feature.home.utils.Resources


class HomeViewModel : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    var homeUiState by mutableStateOf(HomeUIState())

    private val auth = FirebaseAuth.getInstance()


    var hasUser = MutableLiveData(false)

    fun fetchCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            hasUser.value = true
            val user = User(currentUser.displayName ?: "", currentUser.email ?: "")
            homeUiState = homeUiState.copy(dataUser = Resources.Success(user))
        } else {
            hasUser.value = false
        }
    }

    fun logout(){
        FirebaseAuth.getInstance().signOut()
        hasUser.value = false
    }
}