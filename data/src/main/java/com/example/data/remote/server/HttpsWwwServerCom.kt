package com.example.data.remote.server

import com.example.data.model.SomeNetworkDataDto
import com.google.gson.Gson
import kotlin.uuid.Uuid

object HttpsWwwServerCom {
    fun routeResponse(endpoint: String): String {
        when (endpoint) {
            DATA -> {
                val gson = Gson()
                return gson.toJson(someNetworkDataDtoList)
            }
            SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS -> {
                val gson = Gson()
                val newAndroidVersionsList = listOf(
                    SomeNetworkDataDto(
                        id = Uuid.random().toString(),
                        title = VERSION,
                        body = VERSION,
                        dateTime = System.currentTimeMillis() - (123456789)
                    )
                )
                return gson.toJson(newAndroidVersionsList)
            }
            SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS -> {
                val gson = Gson()
                return gson.toJson(androidFeaturesList)
            }
            ADD_AGENT_SKILLS_COROUTINES -> {
                val gson = Gson()
                return gson.toJson(addSkillInstructions)
            }
        }

        error("CloudServer, path not found: $endpoint")
    }
}
