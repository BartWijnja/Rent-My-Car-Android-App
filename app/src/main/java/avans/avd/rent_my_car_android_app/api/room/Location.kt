package avans.avd.rent_my_car_android_app.api.room

import com.squareup.moshi.Json

data class Location(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "street")
    val street: String,

    @Json(name = "houseNumber")
    val houseNumber: String,

    @Json(name = "postalCode")
    val postalCode: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "country")
    val country: String,

    @Json(name = "locationType")
    val locationType: String,

    @Json(name = "createdAt")
    val createdAt: String?
)
