package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import avans.avd.rent_my_car_android_app.model.Location
import com.squareup.moshi.Json

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "firstName")
    val firstName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "phoneNumber")
    val phoneNumber: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "password")
    val password: String?,

    @Json(name = "iban")
    val iban: String,

    @Json(name = "userRole")
    val userRole: String,

    @Json(name = "location")
    val location: Location,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "enabled")
    val enabled: Boolean,

    @Json(name = "locked")
    val locked: Boolean
)
