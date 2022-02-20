package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.model.CarDisplay
import avans.avd.rent_my_car_android_app.network.response.CarDisplayResponse
import retrofit2.Response
import retrofit2.http.*

interface CarDisplayService {
    @GET("/car-displays")
    suspend fun findAll(): Response<List<CarDisplayResponse>>

    @GET("/car-displays/{id}")
    suspend fun findById(@Path("id") id: Long): Response<CarDisplay>

    @GET("/car-displays/{locationId}/display/{carId}")
    suspend fun findByCarType(@Path("locationId") locationId: Long, @Path("carId") carId: Long): Response<CarDisplayResponse>

    @POST("/car-displays/{id}")
    suspend fun post(@Body carDisplay: CarDisplay): Response<CarDisplayResponse>

    @PUT("/car-displays/{id}")
    suspend fun put(@Path("id") id: Long, @Body carDisplay: CarDisplay): Response<CarDisplayResponse>

    @DELETE("/car-displays/{id}")
    suspend fun delete(@Path("id") id: Long)
}
