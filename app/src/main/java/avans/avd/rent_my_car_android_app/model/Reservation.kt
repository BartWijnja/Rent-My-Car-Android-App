package avans.avd.rent_my_car_android_app.model

import avans.avd.rent_my_car_android_app.enums.ReservationStatus

data class Reservation(
    val id: Long = 0,
    val totalPrice: Double,
    val status: ReservationStatus?,
    val daysReserved: Int,
    val user: User,
    val carDisplay: CarDisplay,
    val createdAt: String? = ""
)
