package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import avans.avd.rent_my_car_android_app.model.CarDisplay
import avans.avd.rent_my_car_android_app.model.User
import com.squareup.moshi.Json

@Entity
data class Reservation(

    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "totalPrice")
    val totalPrice: Double,

    @Json(name = "status")
    val status: String?,

    @Json(name = "daysReserved")
    val daysReserved: Int,

    @Json(name = "user")
    val user: User,

    @Json(name = "carDisplay")
    val carDisplay: CarDisplay,

    @Json(name = "createdAt")
    val createdAt: String?
)
