package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse
import avans.avd.rent_my_car_android_app.network.service.CarDisplayService
import retrofit2.Response

class CarDisplayClient (private val carDisplayService: CarDisplayService): BaseClient() {
    suspend fun findAllByLocation(locationId: Long): Response<List<CarDisplayResponse>> {
        return carDisplayService.findAllByLocation(locationId)
    }

    suspend fun findByDisplayWithType(locationId: Long, carType: CarType): Response<List<CarDisplayResponse>?> {
        return carDisplayService.findByDisplayWithType(locationId, carType)
    }

    suspend fun findById(carDisplayId: Long): Response<CarDisplayResponse> {
        return carDisplayService.findById(carDisplayId)
    }
}
