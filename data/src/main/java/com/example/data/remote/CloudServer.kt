package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import com.google.gson.Gson


const val ONE_PATH_SEGMENT = "path"
const val DATA = "data"

object CloudServer {
    fun routeResponse(endpoint:String): String{

        when(endpoint){
            DATA ->{
                val gson = Gson()
                val data = SomeNetworkDataDto(
                    "laskdfj",
                    "$endpoint",
                    "body body body body body",
                    1934509238,
                )
                return gson.toJson(listOf(data))
            }
        }

        error("CloudServer, path not found: $endpoint")
    }

}

