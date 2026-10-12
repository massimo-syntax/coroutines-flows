package com.example.data.remote

import android.util.Log
import com.example.data.remote.server.ADD_AGENT_SKILLS_COROUTINES
import com.example.data.remote.server.DATA
import com.example.data.remote.server.ERROR
import com.example.data.remote.server.HttpsWwwServerCom
import com.example.data.remote.server.SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS
import com.example.data.remote.server.SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS
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
            // RANDOM DATA...
            DATA -> {
                response = server.routeResponse( path )
                code = 200
            }

            // sequential routes to get first android version..
            SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS -> {
                response = server.routeResponse( path )
                code = 200
            }
            // then features of it
            SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS-> {
                response = server.routeResponse( path )
                code = 200
            }

            ADD_AGENT_SKILLS_COROUTINES -> {
                response = server.routeResponse( path )
                code = 200
            }

            // ERROR ROUTE, request to mock an error
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