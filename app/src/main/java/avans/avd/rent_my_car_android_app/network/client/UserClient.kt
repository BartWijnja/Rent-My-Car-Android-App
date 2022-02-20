package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.network.request.LoginRequest
import avans.avd.rent_my_car_android_app.network.response.LoginResponse
import avans.avd.rent_my_car_android_app.network.response.UserResponse
import avans.avd.rent_my_car_android_app.network.service.UserService
import retrofit2.Response

class UserClient (private val userService: UserService): BaseClient() {
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        val loginRequest: LoginRequest = LoginRequest(username, password)
        return userService.login(loginRequest)
    }

    suspend fun findAll(): Response<List<UserResponse>> {
        return userService.findAll()
    }
}
