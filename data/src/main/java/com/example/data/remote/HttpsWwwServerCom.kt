package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import com.google.gson.Gson
import kotlin.uuid.Uuid


const val ONE_PATH_SEGMENT = "path"
const val DATA = "data"
const val ERROR = "error"

const val SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS = "newest_android_versions"

const val SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS = "sequential_features_android_versions"
private const val VERSION = "1.0"


object HttpsWwwServerCom {
    fun routeResponse(endpoint:String): String{
        when(endpoint){
            DATA ->{
                val gson = Gson()
                return gson.toJson(someNetworkDataDtoList)
            }
            SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS ->{
                val gson = Gson()
                val newAndroidVersionsList = listOf(
                    SomeNetworkDataDto(
                        id = Uuid.random().toString(),
                        title =  VERSION ,
                        body = VERSION,
                        dateTime = System.currentTimeMillis() - (123456789)
                    )
                )
                return gson.toJson(newAndroidVersionsList)
            }
            SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS ->{
                val gson = Gson()
                val networkDataList = someNetworkDataDtoList.toMutableList().mapIndexed { index, dto ->
                    dto.copy(
                        title = "$VERSION.$index",
                        body = "description n°$index of version $VERSION.$index"
                    )
                }.toList()
                return gson.toJson(networkDataList)
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


