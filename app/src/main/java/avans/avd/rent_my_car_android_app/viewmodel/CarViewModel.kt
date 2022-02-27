package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.network.response.CarResponse
import avans.avd.rent_my_car_android_app.repository.CarRepository
import kotlinx.coroutines.launch

class CarViewModel: ViewModel() {
    private val carRepository = CarRepository()
    private val _carResult = MutableLiveData<List<CarResponse>>()
    val carResult: LiveData<List<CarResponse>> = _carResult

    fun findAll() {
        viewModelScope.launch {
            val response = carRepository.findAll()
            _carResult.postValue(response!!)
        }
    }

    fun findByCarType(type: String) {
        viewModelScope.launch {
            val response = carRepository.findByCarType(type)
            _carResult.postValue(response!!)
        }
    }
}

