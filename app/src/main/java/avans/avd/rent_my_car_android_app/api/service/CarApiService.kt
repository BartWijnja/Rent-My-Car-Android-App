package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.model.Car
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "${R.string.api_base_href}/cars/"

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

    @GET("/")
    suspend fun findAll(): Response<List<Car>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Car>

    @GET("/name/{name}")
    suspend fun findByName(@Path("name") id: String): Response<List<Car>>

    @GET("/type/{carType}")
    suspend fun findByCarType(@Path("carType") id: String): Response<List<Car>>

    @POST("/")
    suspend fun post(@Body car: Car): Car

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body car: Car): Response<Car>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") carId: Int)

}

object CarApi {
    val retrofitService: CarApiService by lazy {
        retrofit.create(CarApiService::class.java)
    }
}
