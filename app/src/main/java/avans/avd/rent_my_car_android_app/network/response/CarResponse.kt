package avans.avd.rent_my_car_android_app.network.response

data class CarResponse (
    val id: Long,
    val brand: String,
    val brandType: String,
    val model: String,
    val licensePlateNumber: String,
    val consumption: Double,
    val price: Double,
    val personSpace: Int,
    val carType: String,
    val createdAt: String?
)
