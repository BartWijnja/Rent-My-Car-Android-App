package avans.avd.rent_my_car_android_app.network

import avans.avd.rent_my_car_android_app.network.client.CarClient
import avans.avd.rent_my_car_android_app.network.service.CarService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    private const val BASE_URL = "ec2-52-19-164-214.eu-west-1.compute.amazonaws.com:5432/d24i5gbdq6ggme?password=84b87081c63227299edc5ed14407e64280489513eaced95f9088562acb891b32&sslmode=require&user=fgajtkgcieympk"

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