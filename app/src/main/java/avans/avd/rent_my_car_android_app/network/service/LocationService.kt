package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.network.response.LocationResponse
import retrofit2.Response
import retrofit2.http.*

interface LocationService {
    @GET("/")
    suspend fun findAll(): Response<List<LocationResponse>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<LocationResponse>

    @GET("/type/{type}")
    suspend fun findByType(@Path("type") type: CarType): Response<List<LocationResponse>>

    @POST("/")
    suspend fun post(@Body location: LocationResponse): LocationResponse

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body location: LocationResponse): Response<LocationResponse>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") locationId: Int)
}
