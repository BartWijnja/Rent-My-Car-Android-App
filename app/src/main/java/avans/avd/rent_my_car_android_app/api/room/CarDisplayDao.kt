package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDisplayDao {

    @Query(" SELECT * FROM CarDisplay WHERE id = :key ")
    suspend fun getCarDisplay(key: Int): CarDisplay

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCarDisplay(item: CarDisplay): Long

    @Query(" DELETE FROM CarDisplay WHERE id = :key ")
    suspend fun deleteCarDisplay(key: Int)

    @Query(" DELETE FROM CarDisplay ")
    suspend fun clear()
}