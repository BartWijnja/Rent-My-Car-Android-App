package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.model.User
import avans.avd.rent_my_car_android_app.network.request.LoginRequest
import avans.avd.rent_my_car_android_app.network.response.LoginResponse
import avans.avd.rent_my_car_android_app.network.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @GET("/users")
    suspend fun findAll(): Response<List<UserResponse>>

    @GET("/users/{id}")
    suspend fun findById(@Path("id") id: Long): Response<UserResponse>

    @POST("/users/login")
    suspend fun login(@Body body: LoginRequest): Response<LoginResponse>

    @POST("/users")
    suspend fun post(@Body user: User): Response<UserResponse>

    @PUT("/users/{id}")
    suspend fun put(@Path("id") id: Long, @Body user: User): Response<UserResponse>

    @DELETE("/users/{id}")
    suspend fun delete(@Path("id") id: Long)
}
