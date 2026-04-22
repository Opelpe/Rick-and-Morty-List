package com.pnow.data.episode

import com.pnow.domain.repository.EpisodeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class EpisodeRepositoryImpl @Inject constructor(
    private val api: EpisodeApi,
) : EpisodeRepository {
    override fun getEpisode(episodeId: String) =
        flow {
            val episodeDto = api.getEpisode(episodeId)
            emit(episodeDto.toDomain())
        }
}
