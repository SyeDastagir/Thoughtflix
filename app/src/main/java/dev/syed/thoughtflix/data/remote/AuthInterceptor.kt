package dev.syed.thoughtflix.data.remote

import android.util.Log
import dev.syed.thoughtflix.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val request = req
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.API_TOKEN}")
            .build()

        val url = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build()

        req = req.newBuilder().url(url).build()
        Log.d("syedlog","request: ${req.url}")
        return chain.proceed(req)
    }
}