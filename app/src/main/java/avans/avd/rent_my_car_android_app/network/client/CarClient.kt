package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.network.response.BasicResponse
import avans.avd.rent_my_car_android_app.network.response.GetCarResponse
import avans.avd.rent_my_car_android_app.network.service.CarService

class CarClient (private val carService: CarService): BaseClient() {

    suspend fun getCarList(): BasicResponse<List<GetCarResponse>> {
        return safeApiCall { carService.findAll() }
    }

}