package avans.avd.rent_my_car_android_app.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import avans.avd.rent_my_car_android_app.model.Car
import avans.avd.rent_my_car_android_app.model.Location
import com.squareup.moshi.Json

@Entity
data class CarDisplay(

    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "total")
    val total: Int,

    @Json(name = "available")
    val available: Int,

    @Json(name = "location")
    val location: Location?,

    @Json(name = "car")
    val car: Car?,

    @Json(name = "createdAt")
    val createdAt: String?

)
