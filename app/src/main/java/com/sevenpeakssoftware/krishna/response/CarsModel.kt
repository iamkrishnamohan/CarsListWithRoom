package com.sevenpeakssoftware.krishna.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sevenpeakssoftware.krishna.utils.Constants.CARS_TABLE

@Entity(
    tableName = CARS_TABLE
)
data class CarsModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("dateTime")
    val dateTime: String? = null,
    @SerializedName("ingress")
    val ingress: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("created")
    val created: Int? = null,
    @SerializedName("changed")
    val changed: Int? = null
)