package avans.avd.rent_my_car_android_app.network

import avans.avd.rent_my_car_android_app.network.client.CarClient
import avans.avd.rent_my_car_android_app.network.service.CarService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    private const val BASE_URL = "http://192.168.100.21:8080"

    // For parsing the json result: add a Moshi builder
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        // A converter for strings and both primitives and their boxed types to text/plain bodies.
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    // Services
    private val carService: CarService by lazy {
        retrofit.create(CarService::class.java)
    }

    // Clients
    val carClient = CarClient(carService)
}