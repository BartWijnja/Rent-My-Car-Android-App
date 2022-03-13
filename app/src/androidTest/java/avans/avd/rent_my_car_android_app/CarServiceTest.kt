package avans.avd.rent_my_car_android_app

import androidx.test.ext.junit.runners.AndroidJUnit4
import avans.avd.rent_my_car_android_app.network.response.CarResponse
import avans.avd.rent_my_car_android_app.network.service.CarService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CarServiceTest {
    private val carService = Mockito.mock(CarService::class.java)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    @Throws(Exception::class)
    fun carFindAllTest() {
        testScope.runBlockingTest {
            val cars = findAll()
            Mockito.`when`(carService.findAll()).thenReturn(Response.success(cars))

            val carsToTest = carService.findAll()
            assertEquals(carsToTest.body()?.get(0)?.id, cars.get(0).id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun carFindAllFCEVTest() {
        testScope.runBlockingTest {
            val cars = findByTypeFCEV()
            Mockito.`when`(carService.findByCarType("FCEV")).thenReturn(Response.success(cars))

            val carsToTest = carService.findByCarType("FCEV")
            assertEquals(carsToTest.body()?.get(0)?.id, cars.get(0).id)
        }
    }

    private fun findAll(): List<CarResponse> {
        val cars = ArrayList<CarResponse>()
        val car = CarResponse(
            id = 16,
            brand = "Honda",
            brandType = "H-RV",
            model = "EX-L",
            licensePlateNumber = "465-HK-3",
            consumption = 5.7,
            price = 50.0,
            personSpace = 4,
            carType = "ICE",
            createdAt = "2022-01-09T11:34:00.472439"
        )

        cars.add(car)
        return cars
    }

    private fun findByTypeFCEV(): List<CarResponse> {
        val cars = ArrayList<CarResponse>()
        val car = CarResponse(
            id = 18,
            brand = "Hyundai",
            brandType = "ix35",
            model = "FCEV",
            licensePlateNumber = "739-PD-2",
            consumption = 0.59,
            price = 100.0,
            personSpace = 6,
            carType = "FCEV",
            createdAt = "2022-01-09T11:34:00.472439"
        )

        cars.add(car)
        return cars
    }
}
