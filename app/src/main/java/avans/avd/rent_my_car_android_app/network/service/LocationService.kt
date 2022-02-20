package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.model.Location
import avans.avd.rent_my_car_android_app.network.response.LocationResponse
import retrofit2.Response
import retrofit2.http.*

interface LocationService {
    @GET("/locations")
    suspend fun findAll(): Response<List<LocationResponse>>

    @GET("/locations/{id}")
    suspend fun findById(@Path("id") id: Long): Response<LocationResponse>

    @GET("/locations/type/{type}")
    suspend fun findByType(@Path("type") type: CarType): Response<List<LocationResponse>>

    @POST("/locations")
    suspend fun post(@Body location: Location): Response<LocationResponse>

    @PUT("/locations/{id}")
    suspend fun put(@Path("id") id: Long, @Body location: Location): Response<LocationResponse>

    @DELETE("/locations/{id}")
    suspend fun delete(@Path("id") id: Long)
}
