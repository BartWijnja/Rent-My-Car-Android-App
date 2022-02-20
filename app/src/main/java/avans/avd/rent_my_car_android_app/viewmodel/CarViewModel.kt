package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.repository.CarRepository
import kotlinx.coroutines.launch

class CarViewModel: ViewModel() {
    private val carRepository = CarRepository()

    fun findAll() {
        viewModelScope.launch {
            val response = carRepository.findAll()
            println(response)
        }
    }
}