package avans.avd.rent_my_car_android_app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val response = chain.proceed(builder.build())

        if (response.code() == 401) {
            // TODO: add logout here
        }

        return response
    }
}
