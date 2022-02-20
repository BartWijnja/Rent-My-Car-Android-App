package avans.avd.rent_my_car_android_app.network.response

import avans.avd.rent_my_car_android_app.enums.ReservationStatus
import avans.avd.rent_my_car_android_app.model.CarDisplay
import avans.avd.rent_my_car_android_app.model.User

data class ReservationResponse (
    val id: Long,
    val totalPrice: Double,
    val status: ReservationStatus,
    val daysReserved: Int,
    val user: User,
    val carDisplay: CarDisplay,
    val createdAt: String?
)
