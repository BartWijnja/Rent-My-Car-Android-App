package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query(" SELECT * FROM Car WHERE id = :key ")
    suspend fun getCar(key: Int): Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCar(item: Car): Long

    @Query(" DELETE FROM Car WHERE id = :key ")
    suspend fun deleteCar(key: Int)

    @Query(" DELETE FROM Car ")
    suspend fun clear()
}