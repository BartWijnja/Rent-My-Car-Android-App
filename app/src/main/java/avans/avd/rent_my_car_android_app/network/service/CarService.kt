package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.network.response.GetCarResponse
import avans.avd.rent_my_car_android_app.room.Car
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface CarService {

    @GET("/cars")
    suspend fun findAll(): Response<List<GetCarResponse>>

    @GET("/cars/{id}")
    fun findById(@Path("id") id: Long): Response<GetCarResponse>

    @GET("/cars/name/{name}")
    fun findByName(@Path("name") id: String): Response<List<GetCarResponse>>

    @GET("/cars/type/{carType}")
    fun findByCarType(@Path("carType") id: String): Response<List<GetCarResponse>>

    @POST(value = "cars")
    suspend fun postCar(@Body car: Car): Response<GetCarResponse>

    @DELETE("cars/{id}")
    suspend fun deleteCar(@Path("id") carId: Int)

}
