package avans.avd.rent_my_car_android_app.repository

import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse

class CarDisplayRepository {
    suspend fun findAllByLocation(locationId: Long): List<CarDisplayResponse>? {
        val request = Network.carDisplayClient.findAllByLocation(locationId)
        return request.body()
    }

    suspend fun findByDisplayWithType(locationId: Long, carType: CarType): List<CarDisplayResponse>? {
        val request = Network.carDisplayClient.findByDisplayWithType(locationId, carType)
        return request.body()
    }

    suspend fun findById(carDisplayId: Long): CarDisplayResponse? {
        val request = Network.carDisplayClient.findById(carDisplayId)
        return request.body()
    }
}
