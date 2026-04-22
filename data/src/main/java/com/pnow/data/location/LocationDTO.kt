package com.pnow.data.location

import com.google.gson.annotations.SerializedName
import com.pnow.domain.model.Location

data class LocationDTO(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("dimension")
    val dimension: String,
) {
    fun toDomain(): Location {
        return with(this) {
            Location(
                id = id,
                name = name,
                type = type,
                dimension = dimension
            )
        }
    }
}
