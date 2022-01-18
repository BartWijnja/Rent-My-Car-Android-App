package avans.avd.rent_my_car_android_app.api.room

import avans.avd.rent_my_car_android_app.model.Car
import avans.avd.rent_my_car_android_app.model.Location
import com.squareup.moshi.Json

data class CarDisplay(

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
