package avans.avd.rent_my_car_android_app.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query(" SELECT * FROM User WHERE id = :key ")
    suspend fun getUser(key: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(item: User): Long

    @Query(" DELETE FROM User WHERE id = :key ")
    suspend fun deleteUser(key: Int)

    @Query(" DELETE FROM User ")
    suspend fun clear()
}