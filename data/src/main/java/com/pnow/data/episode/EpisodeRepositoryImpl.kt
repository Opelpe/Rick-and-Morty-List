package com.pnow.data.episode

import com.pnow.domain.repository.EpisodeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class EpisodeRepositoryImpl @Inject constructor(private val api: EpisodeApi) : EpisodeRepository {
    override fun getEpisode(episodeId: String) = flow {
        val episodeDto = api.getEpisode(episodeId)
        emit(episodeDto.toDomain())
    }

    override fun getEpisodes(episodeIds: List<String>) = flow {
        val episodes = if (episodeIds.size == 1) {
            listOf(api.getEpisode(episodeIds.first()).toDomain())
        } else {
            api.getEpisodes(episodeIds.joinToString(",")).map { it.toDomain() }
        }
        emit(episodes)
    }
}
