package avans.avd.rent_my_car_android_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import avans.avd.rent_my_car_android_app.network.request.LoginRequest
import avans.avd.rent_my_car_android_app.network.response.LoginResponse
import avans.avd.rent_my_car_android_app.network.service.UserService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserServiceTest {
    private val userService = Mockito.mock(UserService::class.java)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    @Throws(Exception::class)
    fun userServiceTest() {
        testScope.runBlockingTest {
            val user = loginUserResponse()
            val loginRequest = LoginRequest("ipeev", "qwerty")
            Mockito.`when`(userService.login(loginRequest)).thenReturn(Response.success(user))

            val userToTest = userService.login(loginRequest)
            assertEquals(userToTest.body()?.accessToken, user.accessToken)
        }
    }

    private fun loginUserResponse(): LoginResponse {
        return LoginResponse(
            3,
            "blablabla",
        )
    }
}
