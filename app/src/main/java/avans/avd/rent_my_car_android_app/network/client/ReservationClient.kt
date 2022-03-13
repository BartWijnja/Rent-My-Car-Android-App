package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.model.Reservation
import avans.avd.rent_my_car_android_app.network.response.ReservationResponse
import avans.avd.rent_my_car_android_app.network.service.ReservationService
import retrofit2.Response

class ReservationClient (private val reservationService: ReservationService): BaseClient() {
    suspend fun findByUser(userId: Long): Response<List<ReservationResponse>> {
        return reservationService.findByUser(userId)
    }

    suspend fun create(reservation: Reservation) {
        reservationService.post(reservation)
    }
}
