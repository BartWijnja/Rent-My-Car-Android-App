package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.network.response.UserResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @GET("/")
    suspend fun findAll(): Response<List<UserResponse>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<UserResponse>

    @GET("/login")
    suspend fun login(@Body body: JSONObject): Response<JSONObject>

    @POST("/")
    suspend fun post(@Body user: UserResponse): UserResponse

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body user: UserResponse): Response<UserResponse>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") id: Int)
}
