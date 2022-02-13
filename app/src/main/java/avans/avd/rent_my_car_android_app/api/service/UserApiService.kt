package avans.avd.rent_my_car_android_app.api.service

import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "${R.string.api_base_href}/users/"

// For parsing the josn result: add a Moshi builder
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
interface UserApiService {
    @GET("/")
    suspend fun findAll(): Response<List<User>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<User>

    @GET("/login")
    suspend fun login(@Body body: JSONObject): Response<JSONObject>

    @POST("/")
    suspend fun post(@Body user: User): User

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body user: User): Response<User>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") id: Int)
}

object UserApi {
    val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}
