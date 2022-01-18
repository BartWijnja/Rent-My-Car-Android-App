package avans.avd.rent_my_car_android_app.model

data class CarDisplay(
    val id: Long,
    val total: Int,
    val available: Int,
    val location: Location?,
    val car: Car?,
    val createdAt: String?

)
