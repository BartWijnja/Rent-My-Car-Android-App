package avans.avd.rent_my_car_android_app.repository

import avans.avd.rent_my_car_android_app.model.LoggedInUser
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.ui.login.data.LoginDataSource
import avans.avd.rent_my_car_android_app.util.Result

class LoginRepository(val dataSource: LoginDataSource) {
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Result<LoggedInUser>? {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}
