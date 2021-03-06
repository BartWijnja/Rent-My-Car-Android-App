package avans.avd.rent_my_car_android_app.repository

import android.widget.Toast
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.network.response.LoginResponse
import avans.avd.rent_my_car_android_app.network.response.UserResponse

class UserRepository {
    suspend fun login(username: String, password: String): LoginResponse? {
        val request = Network.userClient.login(username, password)

        if (!request.isSuccessful) {
            if (request.code() == 401) {
                Toast.makeText(RentMyCarApplication.context, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
            return null
        }

        return request.body()
    }

    suspend fun findAll(): List<UserResponse>? {
        val request = Network.userClient.findAll()
        return request.body()
    }

    suspend fun find(id: Long): UserResponse? {
        val request = Network.userClient.find(id)
        return request.body()
    }
}
