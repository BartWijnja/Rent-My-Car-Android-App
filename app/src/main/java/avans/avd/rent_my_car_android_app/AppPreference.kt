package avans.avd.rent_my_car_android_app

import android.content.Context
import android.content.SharedPreferences

class AppPreference(context: Context) {
    private val PREFERENCE_NAME = "SharedPreference"
    private val PREFERENCE_TOKEN = "AccessToken"
    private val PREFERENCE_USER_ID  = "userId"
    private val PREFERENCE_LOCATION_ID = "locationId"

    private val preference: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getToken() : String? {
        return preference.getString(PREFERENCE_TOKEN, "token")
    }

    fun setToken(token: String?) {
        preference.edit().putString(PREFERENCE_TOKEN, token).apply()
    }

    fun getUserId() : Int {
        return preference.getInt(PREFERENCE_USER_ID, 0)
    }

    fun setUserId(userId: Int) {
        preference.edit().putInt(PREFERENCE_USER_ID, userId).apply()
    }

    fun getLocationId() : Int {
        return preference.getInt(PREFERENCE_LOCATION_ID, 0)
    }

    fun setLocationId(locationId: Int) {
        preference.edit().putInt(PREFERENCE_LOCATION_ID, locationId).apply()
    }

    fun clearPreferences() {
        preference.edit().clear().apply()
    }
}