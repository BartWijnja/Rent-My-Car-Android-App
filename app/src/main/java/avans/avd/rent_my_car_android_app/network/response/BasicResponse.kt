package avans.avd.rent_my_car_android_app.network.response

import retrofit2.Response
import java.lang.Exception

data class BasicResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    // Make a BasicResponse out of the API request for error handling
    companion object {
        fun <T> success(data: Response<T>): BasicResponse<T> {
            return BasicResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): BasicResponse<T> {
            return BasicResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!
}
