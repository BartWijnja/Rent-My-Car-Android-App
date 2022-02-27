package avans.avd.rent_my_car_android_app.ui.login.data

import android.widget.Toast
import avans.avd.rent_my_car_android_app.AppPreference
import avans.avd.rent_my_car_android_app.RentMyCarApplication
import avans.avd.rent_my_car_android_app.model.LoggedInUser
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.util.Result
import java.io.IOException
import java.util.*

class LoginDataSource {
    private val preference = AppPreference(RentMyCarApplication.context)

    suspend fun login(username: String, password: String): Result<LoggedInUser>? {
        val request = Network.userClient.login(username, password)

        if (!request.isSuccessful) {
            if (request.code() == 401) {
                Toast.makeText(RentMyCarApplication.context, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
            return Result.Error(IOException("Error logging in", null))
        }

        preference.clearPreferences()
        preference.setToken(request.body()?.accessToken)
        request.body()?.id?.let { preference.setUserId(it) }

        val user = LoggedInUser(UUID.randomUUID().toString(), request.body()?.id.toString())

        return Result.Success(user)
    }

    fun logout() {
        preference.clearPreferences()
    }
}
