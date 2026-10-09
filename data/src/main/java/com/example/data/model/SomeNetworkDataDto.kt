package com.example.data.model

import com.example.domain.model.MyData
import com.google.gson.annotations.SerializedName
import kotlin.uuid.Uuid

data class SomeNetworkDataDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    var title: String?,

    @SerializedName("body")
    var body: String?,

    @SerializedName("datetime")
    val dateTime: Long?,

    @SerializedName("signature")
    val signature: String? = Uuid.random().toHexDashString()
)

fun SomeNetworkDataDto.toMyData() =
    MyData(
        id,
        title,
        body,
        dateTime
    )

fun List<SomeNetworkDataDto>.toMyData() = map{ it.toMyData() }
