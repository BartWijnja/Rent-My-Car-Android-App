package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.network.response.LoginResponse
import avans.avd.rent_my_car_android_app.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val userRepository = UserRepository()

    private val _userLoginLiveData = MutableLiveData<LoginResponse?>()
    val userLoginLiveData: LiveData<LoginResponse?> = _userLoginLiveData

    fun findAll() {
        viewModelScope.launch {
            val response = userRepository.findAll()
            println(response)
        }
    }
}
