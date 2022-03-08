package avans.avd.rent_my_car_android_app.viewmodel

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import avans.avd.rent_my_car_android_app.R
import avans.avd.rent_my_car_android_app.ui.login.LoginFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
   private lateinit var scenario: FragmentScenario<LoginFragment>

    @Before
    fun setup() {
        val mockNavController = mock(NavController::class.java)
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_RentMyCarAndroidApp)
        scenario.moveToState(Lifecycle.State.STARTED)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
    }

    @Test
    fun testLoginUser() {
        val username = "ipeev"
        val password = "qwerty"

        onView(withId(R.id.username)).perform(typeText(username))
        onView(withId(R.id.password)).perform(typeText(password))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.login)).perform(click())
    }
}
