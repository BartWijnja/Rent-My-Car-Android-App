package avans.avd.rent_my_car_android_app.repository

import android.content.Context
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.room.RmcDatabase

class CarRepository {

    private fun client() = Network.carClient
    private fun dao(context : Context) = RmcDatabase.getInstance(context).carDao()

}