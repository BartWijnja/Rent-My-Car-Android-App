package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse
import retrofit2.Response
import retrofit2.http.*

interface CarDisplayService {
    @GET("/")
    suspend fun findAll(): Response<List<CarDisplayResponse>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<CarDisplayResponse>

    @GET("/{locationId}/display/{carId}")
    suspend fun findByCarType(@Path("locationId") locationId: Long, @Path("carId") carId: Long): Response<CarDisplayResponse>

    @POST("/")
    suspend fun post(@Body carDisplay: CarDisplayResponse): CarDisplayResponse

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body carDisplay: CarDisplayResponse): Response<CarDisplayResponse>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") carDisplayId: Int)
}
