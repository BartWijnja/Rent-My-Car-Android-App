package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.api.item.CarItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://192.168.100.21:8080"

// For parsing the json result: add a Moshi builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    // A converter for strings and both primitives and their boxed types to text/plain bodies.
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Here we define how Retrofit interacts with the webservice
// we create 'suspend' fun, so we can call the function from a coroutine scope
interface CarApiService {

    @GET("/cars")
    suspend fun findAll(): Response<List<CarItem>>

    @GET("/cars/{id}")
    fun findById(@Path("id") id: Long): Response<CarItem>

    @GET("/cars/name/{name}")
    fun findByName(@Path("name") id: String): Response<List<CarItem>>

    @GET("/cars/type/{carType}")
    fun findByCarType(@Path("carType") id: String): Response<List<CarItem>>

    @POST(value = "cars")
    suspend fun postCar(@Body carItem: CarItem): CarItem

    @DELETE("cars/{id}")
    suspend fun deleteCar(@Path("id") carId: Int)

}

object CarApi {
    val retrofitService: CarApiService by lazy {
        retrofit.create(CarApiService::class.java)
    }
}