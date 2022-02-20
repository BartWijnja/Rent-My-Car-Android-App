package avans.avd.rent_my_car_android_app.network.response

import avans.avd.rent_my_car_android_app.enums.LocationType

data class LocationResponse (
    val id: Long,
    val street: String,
    val houseNumber: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val locationType: LocationType,
    val createdAt: String?
)
