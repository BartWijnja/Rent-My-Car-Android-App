package avans.avd.rent_my_car_android_app.model

data class User (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val iban: String,
    val userRole: String,
    val location: Location,
    val enabled: Boolean,
    val locked: Boolean,
    val createdAt: String?
)
