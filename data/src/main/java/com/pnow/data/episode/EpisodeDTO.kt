package com.pnow.data.episode

import com.google.gson.annotations.SerializedName
import com.pnow.domain.model.Episode

data class EpisodeDTO(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("air_date")
    val airDate: String,
    @field:SerializedName("episode")
    val episode: String,
) {
    fun toDomain(): Episode {
        return with(this) {
            Episode(
                id = id,
                name = name,
                airDate = airDate,
                episodeNumber = episode
            )
        }
    }
}
