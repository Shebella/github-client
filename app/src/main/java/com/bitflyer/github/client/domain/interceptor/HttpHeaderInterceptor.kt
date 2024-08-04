package com.bitflyer.github.client.domain.interceptor

import com.bitflyer.github.client.BuildConfig
import com.bitflyer.github.client.domain.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * "message":"API rate limit exceeded (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)",
 * "documentation_url":"https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"}
 */
class HttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (BuildConfig.PERSONAL_ACCESS_TOKEN.isNotBlank()) {
            builder.addHeader(Constants.HEADER_AUTHORIZATION, "${Constants.HEADER_BEARER} ${BuildConfig.PERSONAL_ACCESS_TOKEN}")
        }

        return chain.proceed(builder.build())
    }
}
