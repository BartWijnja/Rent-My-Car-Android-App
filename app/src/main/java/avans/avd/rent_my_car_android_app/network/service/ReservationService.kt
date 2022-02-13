package avans.avd.rent_my_car_android_app.network.service

import avans.avd.rent_my_car_android_app.network.response.ReservationResponse
import retrofit2.Response
import retrofit2.http.*

interface ReservationService {
    @GET("/")
    suspend fun findAll(): Response<List<ReservationResponse>>

    @GET("/{id}")
    suspend fun findById(@Path("id") id: Long): Response<ReservationResponse>

    @GET("/{userId}/reservation/{carDisplayId}")
    suspend fun findByReservation(@Path("userId") userId: Long, @Path("carDisplayId") carDisplayId: Long): Response<ReservationResponse>

    @POST("/")
    suspend fun post(@Body reservation: ReservationResponse): ReservationResponse

    @PUT("/{id}")
    suspend fun put(@Path("id") id: Int, @Body reservation: ReservationResponse): Response<ReservationResponse>

    @DELETE("/{id}")
    suspend fun delete(@Path("id") id: Int)
}
