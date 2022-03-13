package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse
import avans.avd.rent_my_car_android_app.repository.CarDisplayRepository
import kotlinx.coroutines.launch

class CarDisplayViewModel: ViewModel() {
    private val carDisplayRepository = CarDisplayRepository()
    private val _carDisplayResult = MutableLiveData<List<CarDisplayResponse>>()
    private val _singleCarDisplayResult = MutableLiveData<CarDisplayResponse>()
    val carDisplayResult: LiveData<List<CarDisplayResponse>> = _carDisplayResult
    val singleCarDisplayResult: LiveData<CarDisplayResponse> = _singleCarDisplayResult

    fun findAllByLocation(locationId: Long) {
        viewModelScope.launch {
            val response = carDisplayRepository.findAllByLocation(locationId)
            _carDisplayResult.postValue(response!!)
        }
    }

    fun findByDisplayWithType(locationId: Long, carType: CarType) {
        viewModelScope.launch {
            val response = carDisplayRepository.findByDisplayWithType(locationId, carType)
            _carDisplayResult.postValue(response!!)
        }
    }

    fun findById(carDisplayId: Long) {
        viewModelScope.launch {
            val response = carDisplayRepository.findById(carDisplayId)
            _singleCarDisplayResult.postValue(response!!)
        }
    }
}
