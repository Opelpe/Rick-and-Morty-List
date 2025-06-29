package com.pnow.data.episode

import com.pnow.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val api: EpisodeApi,
    private val mapper: EpisodeDTOMapper
) : EpisodeRepository {
    override fun getEpisode(episodeId: String) =
        flow {
            val episodeDto = api.getEpisode(episodeId)
            emit(mapper.map(episodeDto))
        }
}
