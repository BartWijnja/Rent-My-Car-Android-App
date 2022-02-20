package avans.avd.rent_my_car_android_app.network

import avans.avd.rent_my_car_android_app.network.client.*
import avans.avd.rent_my_car_android_app.network.interceptor.AuthInterceptor
import avans.avd.rent_my_car_android_app.network.interceptor.JwtInterceptor
import avans.avd.rent_my_car_android_app.network.service.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val BASE_URL = "https://obscure-stream-81565.herokuapp.com"

    // For parsing the json result: add a Moshi builder
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(getLoggingHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    // Services
    private val carDisplayService: CarDisplayService by lazy {
        retrofit.create(CarDisplayService::class.java)
    }
    private val carService: CarService by lazy {
        retrofit.create(CarService::class.java)
    }
    private val locationService: LocationService by lazy {
        retrofit.create(LocationService::class.java)
    }
    private val reservationService: ReservationService by lazy {
        retrofit.create(ReservationService::class.java)
    }
    private val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    // Clients
    val carDisplayClient = CarDisplayClient(carDisplayService)
    val carClient = CarClient(carService)
    val locationClient = LocationClient(locationService)
    val reservationClient = ReservationClient(reservationService)
    val userClient = UserClient(userService)

    private fun getLoggingHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(JwtInterceptor())
        client.addInterceptor(AuthInterceptor())

        return client.build()
    }
}
