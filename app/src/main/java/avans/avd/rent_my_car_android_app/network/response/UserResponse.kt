package avans.avd.rent_my_car_android_app.network.response

import avans.avd.rent_my_car_android_app.enums.UserRole
import avans.avd.rent_my_car_android_app.model.Location

data class UserResponse (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val username: String,
    val password: String,
    val iban: String,
    val userRole: UserRole,
    val enabled: Boolean,
    val locked: Boolean,
    val location: Location,
    val createdAt: String?
)
