package com.sevenpeakssoftware.krishna.response


import com.google.gson.annotations.SerializedName

data class CarsDetailsResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("content")
    val content: List<Content?>? = null,
    @SerializedName("serverTime")
    val serverTime: Int? = null
) {
    data class Content(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("dateTime")
        val dateTime: String? = null,
        @SerializedName("tags")
        val tags: List<Any?>? = null,
        @SerializedName("content")
        val content: List<Content?>? = null,
        @SerializedName("ingress")
        val ingress: String? = null,
        @SerializedName("image")
        val image: String? = null,
        @SerializedName("created")
        val created: Int? = null,
        @SerializedName("changed")
        val changed: Int? = null
    ) {
        data class Content(
            @SerializedName("type")
            val type: String? = null,
            @SerializedName("subject")
            val subject: String? = null,
            @SerializedName("description")
            val description: String? = null
        )
    }
}
