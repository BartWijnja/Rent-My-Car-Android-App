package avans.avd.rent_my_car_android_app.repository

import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.network.response.LocationResponse

class LocationRepository {
    suspend fun findAllDisplays(): List<LocationResponse>? {
        val request = Network.locationClient.findAllDisplays()
        return request.body()
    }
}
