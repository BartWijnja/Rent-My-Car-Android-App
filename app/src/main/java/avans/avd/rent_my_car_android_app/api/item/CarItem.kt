package avans.avd.rent_my_car_android_app.api.item

import com.squareup.moshi.Json

data class CarItem(

    @Json(name = "car_id")
    val id: Int? = null,

    @Json(name = "brand")
    val brand: String,

    @Json(name = "brandType")
    val brandType: String,

    @Json(name = "model")
    val model: String,

    @Json(name = "licensePlateNumber")
    val licensePlateNumber: String,

    @Json(name = "consumption")
    val consumption: Double,

    @Json(name = "price")
    val price: Double,

    @Json(name = "personSpace")
    val personSpace: Int,

    @Json(name = "carType")
    val carType: String,

    @Json(name = "createdAt")
    val createdAt: String?
)
