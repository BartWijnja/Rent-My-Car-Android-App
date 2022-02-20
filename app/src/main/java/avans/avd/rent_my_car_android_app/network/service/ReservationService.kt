package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.model.Reservation
import avans.avd.rent_my_car_android_app.network.response.ReservationResponse
import retrofit2.Response
import retrofit2.http.*

interface ReservationService {
    @GET("/reservations")
    suspend fun findAll(): Response<List<ReservationResponse>>

    @GET("/reservations/{id}")
    suspend fun findById(@Path("id") id: Long): Response<ReservationResponse>

    @GET("/reservations/{userId}/reservation/{carDisplayId}")
    suspend fun findByReservation(@Path("userId") userId: Long, @Path("carDisplayId") carDisplayId: Long): Response<ReservationResponse>

    @POST("/reservations")
    suspend fun post(@Body reservation: Reservation): Response<ReservationResponse>

    @PUT("/reservations/{id}")
    suspend fun put(@Path("id") id: Long, @Body reservation: Reservation): Response<ReservationResponse>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") id: Long)
}
