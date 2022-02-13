package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.model.Car
import avans.avd.rent_my_car_android_app.model.CarDisplay
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "${R.string.api_base_href}/car-displays";

// For parsing the json result: add a Moshi builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    // A converter for strings and both primitives and their boxed types to text/plain bodies.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Here we define how Retrofit interacts with the webservice
// we create 'suspend' fun, so we can call the function from a coroutine scope
interface CarDisplayApiService {
    @GET("/")
    suspend fun findAll(): Response<List<CarDisplay>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<CarDisplay>

    @GET("/{locationId}/display/{carId}")
    suspend fun findByCarType(@Path("locationId") locationId: Long, @Path("carId") carId: Long): Response<CarDisplay>

    @POST("/")
    suspend fun post(@Body carDisplay: CarDisplay): CarDisplay

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body carDisplay: CarDisplay): Response<CarDisplay>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") carDisplayId: Int)
}

object CarDisplayApi {
    val retrofitService: CarDisplayApiService by lazy {
        retrofit.create(CarDisplayApiService::class.java)
    }
}
