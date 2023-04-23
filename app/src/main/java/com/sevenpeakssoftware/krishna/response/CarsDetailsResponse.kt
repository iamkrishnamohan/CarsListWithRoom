package com.sevenpeakssoftware.krishna.response


import com.google.gson.annotations.SerializedName

data class CarsDetailsResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("content")
    val content: List<CarsModel?>? = null,
    @SerializedName("serverTime")
    val serverTime: Int? = null
)
