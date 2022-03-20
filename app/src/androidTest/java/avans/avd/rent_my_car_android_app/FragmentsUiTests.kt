package avans.avd.rent_my_car_android_app

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentsUiTests {
    @Rule
    @JvmField
    val rule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var decorView: View

    @Before
    fun setup() {
        rule.scenario.onActivity {
            decorView = it.window.decorView
        }
    }

    fun waitFor(millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("avans.avd.rent_my_car_android_app", appContext.packageName)
    }

    @Test
    fun testLogin() {
        onView(withId(R.id.username)).perform(typeText("ipeev"))
        onView(withId(R.id.password)).perform(typeText("qwerty"))
        onView(withId(R.id.login)).perform(click())

        onView(isRoot()).perform(waitFor(1000))

        onView(withText("Home"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAllCars() {
        onView(withId(R.id.username)).perform(typeText("ipeev"))
        onView(withId(R.id.password)).perform(typeText("qwerty"))
        onView(withId(R.id.login)).perform(click())

        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.button_maps)).perform(click())
        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.sp_locations)).perform((click()))
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("HQ Utrecht"))).perform(click())
        onView(isRoot()).perform(waitFor(1000))

        Espresso.pressBack()
        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.button_get_all)).perform(click())

        onView(isRoot()).perform(waitFor(3000))

        onData(Matchers.hasToString(startsWith("Honda")))
            .inAdapterView(withId(R.id.listview_carlist)).atPosition(0)
            .perform(click())
    }

    @Test
    fun testCarsByCategory() {
        onView(withId(R.id.username)).perform(typeText("ipeev"))
        onView(withId(R.id.password)).perform(typeText("qwerty"))
        onView(withId(R.id.login)).perform(click())

        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.button_maps)).perform(click())
        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.sp_locations)).perform((click()))
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("HQ Utrecht"))).perform(click())
        onView(isRoot()).perform(waitFor(1000))

        Espresso.pressBack()
        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.get_by_type)).perform(click())

        onView(isRoot()).perform(waitFor(3000))

        onView(withId(R.id.sp_car_type)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("FCEV"))).perform(click())

        onView(isRoot()).perform(waitFor(1000))

        onData(Matchers.hasToString(startsWith("Peugeot")))
            .inAdapterView(withId(R.id.list_car_category)).atPosition(0)
            .perform(click())
    }
}
