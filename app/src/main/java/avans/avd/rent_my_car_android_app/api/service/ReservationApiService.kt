package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.model.Reservation
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "${R.string.api_base_href}/reservations/"

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
interface ReservationApiService {
    @GET("/")
    suspend fun findAll(): Response<List<Reservation>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<Reservation>

    @GET("/{userId}/reservation/{carDisplayId}")
    suspend fun findByReservation(@Path("userId") userId: Long, @Path("carDisplayId") carDisplayId: Long): Response<Reservation>

    @POST("/")
    suspend fun post(@Body reservation: Reservation): Reservation

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body reservation: Reservation): Response<Reservation>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") id: Int)
}

object ReservationApi {
    val retrofitService: ReservationApiService by lazy {
        retrofit.create(ReservationApiService::class.java)
    }
}
