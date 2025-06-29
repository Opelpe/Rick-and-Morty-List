package com.pnow.data.episode

import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("air_date")
    val airDate: String,
    @field:SerializedName("episode")
    val episode: String
)
