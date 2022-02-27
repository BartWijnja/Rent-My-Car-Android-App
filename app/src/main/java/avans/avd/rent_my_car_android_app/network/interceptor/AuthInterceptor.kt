package avans.avd.rent_my_car_android_app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}
