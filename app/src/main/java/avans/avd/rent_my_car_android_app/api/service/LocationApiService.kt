package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.enums.CarType
import avans.avd.rent_my_car_android_app.model.Location
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "${R.string.api_base_href}/locations/"

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
interface LocationApiService {
    @GET("/")
    suspend fun findAll(): Response<List<Location>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Location>

    @GET("/type/{type}")
    suspend fun findByType(@Path("type") type: CarType): Response<List<Location>>

    @POST("/")
    suspend fun post(@Body location: Location): Location

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body location: Location): Response<Location>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") locationId: Int)
}

object LocationApi {
    val retrofitService: LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }
}
