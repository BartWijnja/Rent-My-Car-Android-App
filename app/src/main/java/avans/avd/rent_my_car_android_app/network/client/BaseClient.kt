package avans.avd.rent_my_car_android_app.network.client

import avans.avd.rent_my_car_android_app.network.response.BasicResponse
import retrofit2.Response

abstract class BaseClient {

    // Rewrites response to a safe SimpleResponse used for error handling
    inline fun <T> safeApiCall(apiCall: () -> Response<T>): BasicResponse<T> {
        return try {
            BasicResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            BasicResponse.failure(e)
        }
    }
}