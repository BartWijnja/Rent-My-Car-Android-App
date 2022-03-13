package avans.avd.rent_my_car_android_app.repository

import avans.avd.rent_my_car_android_app.model.Reservation
import avans.avd.rent_my_car_android_app.network.Network
import avans.avd.rent_my_car_android_app.network.response.ReservationResponse

class ReservationRepository {
    suspend fun findByUser(userId: Long): List<ReservationResponse>? {
        val request = Network.reservationClient.findByUser(userId)
        return request.body()
    }

    suspend fun create(reservation: Reservation) {
        Network.reservationClient.create(reservation)
    }
}
