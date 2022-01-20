package avans.avd.rent_my_car_android_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query(" SELECT * FROM cars WHERE id = :key ")
    suspend fun getCar(key: Int): Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCar(item: Car): Long

    @Query(" DELETE FROM cars WHERE id = :key ")
    suspend fun deleteCar(key: Int)

    @Query(" DELETE FROM cars ")
    suspend fun clear()
}