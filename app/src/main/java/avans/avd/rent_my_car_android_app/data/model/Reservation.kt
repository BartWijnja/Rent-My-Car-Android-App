package avans.avd.rent_my_car_android_app.data.model

data class Reservation(
    val id: Long,
    val totalPrice: Double,
    val status: String?,
    val daysReserved: Int,
    val user: User,
    val carDisplay: CarDisplay,
    val createdAt: String

)
