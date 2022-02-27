package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.network.response.CarResponse
import avans.avd.rent_my_car_android_app.network.service.CarService
import retrofit2.Response

class CarClient (private val carService: CarService): BaseClient() {
    suspend fun findAll(): Response<List<CarResponse>> {
        return carService.findAll()
    }
}
