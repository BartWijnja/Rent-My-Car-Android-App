package avans.avd.rent_my_car_android_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.model.Reservation
import avans.avd.rent_my_car_android_app.network.response.ReservationResponse
import avans.avd.rent_my_car_android_app.repository.ReservationRepository
import kotlinx.coroutines.launch

class ReservationViewModel: ViewModel() {
    private val reservationRepository = ReservationRepository()
    private val _reservationResult = MutableLiveData<List<ReservationResponse>>()
    val reservationResult: LiveData<List<ReservationResponse>> = _reservationResult

    fun findByUser(userId: Long) {
        viewModelScope.launch {
            val response = reservationRepository.findByUser(userId)
            _reservationResult.postValue(response!!)
        }
    }

    fun create(reservation: Reservation) {
        viewModelScope.launch {
            reservationRepository.create(reservation)
        }
    }
}
