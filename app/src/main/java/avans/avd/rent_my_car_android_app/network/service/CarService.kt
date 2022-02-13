package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.network.response.CarResponse
import avans.avd.rent_my_car_android_app.room.Car
import retrofit2.Response
import retrofit2.http.*

interface CarService {

    @GET("/cars")
    suspend fun findAll(): Response<List<CarResponse>>

    @GET("/cars/{id}")
    fun findById(@Path("id") id: Long): Response<CarResponse>

    @GET("/cars/name/{name}")
    fun findByName(@Path("name") id: String): Response<List<CarResponse>>

    @GET("/cars/type/{carType}")
    fun findByCarType(@Path("carType") id: String): Response<List<CarResponse>>

    @POST(value = "/cars")
    suspend fun post(@Body car: Car): Response<CarResponse>

    @PUT("/cars/{id}")
    suspend fun put(@Path("id") id: Int, @Body car: Car): Response<CarResponse>

    @DELETE("/cars/{id}")
    suspend fun delete(@Path("id") carId: Int)

}
