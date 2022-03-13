package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.network.response.LocationResponse
import avans.avd.rent_my_car_android_app.repository.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val locationRepository = LocationRepository()
    private val _locationResult = MutableLiveData<List<LocationResponse>>()
    val locationResult: LiveData<List<LocationResponse>> = _locationResult

    fun findAllDisplays() {
        viewModelScope.launch {
            val response = locationRepository.findAllDisplays()
            _locationResult.postValue(response!!)
        }
    }
}