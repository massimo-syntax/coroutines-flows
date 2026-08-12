package com.example.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(
    private val server : CloudServer
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url

        val response = server.routeResponse( url.encodedPathSegments.last() )

        val responseSuccess = Response.Builder()
            .code(200)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(
                response.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()

        return responseSuccess

    }
}