package com.pnow.ramlist.app.data.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("dimension")
    val dimension: String,
)
