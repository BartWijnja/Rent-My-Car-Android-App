package avans.avd.rent_my_car_android_app.network.response

import avans.avd.rent_my_car_android_app.model.Car
import avans.avd.rent_my_car_android_app.model.Location

data class CarDisplayResponse (
    val id: Long,
    val total: Int,
    val available: Int,
    val location: Location,
    val car: Car,
    val createdAt: String?
)
