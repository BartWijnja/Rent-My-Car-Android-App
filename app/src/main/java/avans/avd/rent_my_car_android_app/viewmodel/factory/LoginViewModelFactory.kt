package avans.avd.rent_my_car_android_app.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import avans.avd.rent_my_car_android_app.repository.LoginRepository
import avans.avd.rent_my_car_android_app.ui.login.data.LoginDataSource
import avans.avd.rent_my_car_android_app.viewmodel.LoginViewModel

class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(mainRepository = LoginRepository(dataSource = LoginDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}