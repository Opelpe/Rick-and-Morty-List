package com.pnow.domain.repository

import com.pnow.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisode(
        episodeId: String
    ): Flow<Episode>
}
