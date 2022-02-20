package avans.avd.rent_my_car_android_app.repository

import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.network.response.CarResponse

class CarRepository {
    suspend fun findAll(): List<CarResponse>? {
        val request = Network.carClient.findAll()
        return request.body()
    }
}