package com.example.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(
    private val server : HttpsWwwServerCom
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        var response = "MockInterceptor: ... not found ..."
        var code = 404
        var message = ""

        val url = chain.request().url
        val path = url.encodedPathSegments.last()

        when(path){
            DATA -> {
                response = server.routeResponse( path )
                code = 200
            }
            ERROR -> {
                code = 404
                message = "MockInterceptor: Not found"
                response = "[]"
            }
        }


        return Response.Builder()
            .code(code)
            .message(message)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(
                response.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()


    }
}



// This works as well
/*
        val responseError = Response.Builder()
            .code(404)
            .message("not found")
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .build()
 */