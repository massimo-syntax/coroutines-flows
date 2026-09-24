package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import com.google.gson.Gson
import kotlin.uuid.Uuid


const val ONE_PATH_SEGMENT = "path"
const val DATA = "data"
const val ERROR = "error"


object HttpsWwwServerCom {
    fun routeResponse(endpoint:String): String{
        when(endpoint){
            DATA ->{
                val gson = Gson()
                return gson.toJson(someNetworkDataDtoList)
            }

        }

        error("CloudServer, path not found: $endpoint")
    }

}


val someNetworkDataDtoList: List<SomeNetworkDataDto> = List(50) { index ->
    SomeNetworkDataDto(
        id = Uuid.random().toString(),
        title = "Title ${index + 1}",
        body = "Body content for item ${index + 1}",
        dateTime = System.currentTimeMillis() - (index * 86_400_000L)
    )
}

