package avans.avd.rent_my_car_android_app.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import avans.avd.rent_my_car_android_app.api.room.Car
import avans.avd.rent_my_car_android_app.api.service.CarApi
import kotlinx.coroutines.launch


private const val TAG = "CarViewModel"

class CarViewModel : ViewModel() {

    private val _carResponse: MutableLiveData<String> = MutableLiveData()

    val carResponse: LiveData<String> // todo: voor demo String, later List<TodoItem>
        get() = _carResponse

    init {
        getCarItems()
    }

    fun getCarItems() {
        viewModelScope.launch {
            try {
                Log.i(TAG, "getCars: launch started")
                _carResponse.value = CarApi.retrofitService.findAll().toString()
            } catch (e: Exception) {
                _carResponse.value = e.message.toString()
            }
        }
    }

    fun postCarItem(car: Car) {
        viewModelScope.launch {
            try {
                CarApi.retrofitService.postCar(car)
                Log.i(TAG, "postCar: $car posted")
                _carResponse.value = "postCar: $car posted"
            } catch (e: Exception) {
                _carResponse.value = e.message.toString()
            }
        }
    }

    fun deleteCarItem(carId: Int) {
        viewModelScope.launch {
            try {
                CarApi.retrofitService.deleteCar(carId)
                Log.i(TAG, "deleteCar: $carId deleted")
                _carResponse.value = "deleteCar: $carId deleted"
            }  catch (e: Exception) {
                _carResponse.value = e.message.toString()
            }
        }
    }


}