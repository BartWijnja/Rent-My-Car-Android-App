package avans.avd.rent_my_car_android_app.model

data class Location(
    val id: Long,
    val street: String,
    val houseNumber: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val locationType: String,
    val latitude: Double,
    val longitude: Double,
    val title: String,
    val createdAt: String?
)
