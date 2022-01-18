package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReservationDao {

    @Query(" SELECT * FROM Reservation WHERE id = :key ")
    suspend fun getReservation(key: Int): Reservation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createReservation(item: Reservation): Long

    @Query(" DELETE FROM Reservation WHERE id = :key ")
    suspend fun deleteReservation(key: Int)

    @Query(" DELETE FROM Reservation ")
    suspend fun clear()
}