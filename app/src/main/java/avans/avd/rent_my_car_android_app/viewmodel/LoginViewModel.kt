package avans.avd.rent_my_car_android_app.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.repository.LoginRepository
import avans.avd.rent_my_car_android_app.ui.login.LoggedInUserView
import avans.avd.rent_my_car_android_app.ui.login.LoginFormState
import avans.avd.rent_my_car_android_app.ui.login.LoginResult
import avans.avd.rent_my_car_android_app.util.Result
import kotlinx.coroutines.launch

class LoginViewModel(private val mainRepository: LoginRepository) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String, navController: NavController) {
        viewModelScope.launch {
            val result = mainRepository.login(username, password)

            if (result is Result.Success) {
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
                navController.navigate(R.id.action_LoginFragment_to_HomeFragment)
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            mainRepository.logout()
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
