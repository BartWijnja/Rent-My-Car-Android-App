package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.model.Car
import avans.avd.rent_my_car_android_app.network.response.CarResponse
import retrofit2.Response
import retrofit2.http.*

interface CarService {
    @GET("/cars")
    suspend fun findAll(): Response<List<CarResponse>>

    @GET("/cars/{id}")
    suspend fun findById(@Path("id") id: Long): Response<CarResponse>

    @GET("/cars/name/{name}")
    suspend fun findByName(@Path("name") name: String): Response<List<CarResponse>>

    @POST("/cars")
    suspend fun post(@Body car: Car): Response<CarResponse>

    @PUT("/cars/{id}")
    suspend fun put(@Path("id") id: Long, @Body car: Car): Response<CarResponse>

    @DELETE("/cars/{id}")
    suspend fun delete(@Path("id") id: Long)
}
