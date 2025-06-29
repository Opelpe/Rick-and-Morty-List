package com.pnow.data.episode

import com.pnow.domain.model.Episode
import javax.inject.Inject

class EpisodeDTOMapper @Inject constructor() {
    fun map(dto: EpisodeDTO): Episode {
        return with(dto) {
            Episode(
                id = id,
                name = name,
                airDate = airDate,
                episode = episode
            )
        }
    }
}
