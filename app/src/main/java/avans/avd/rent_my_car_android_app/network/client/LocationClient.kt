package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.enums.LocationType
import avans.avd.rent_my_car_android_app.network.response.LocationResponse
import avans.avd.rent_my_car_android_app.network.service.LocationService
import retrofit2.Response

class LocationClient (private val locationService: LocationService): BaseClient() {
    suspend fun findAllDisplays(): Response<List<LocationResponse>> {
        return locationService.findByType(LocationType.DISPLAY)
    }
}
