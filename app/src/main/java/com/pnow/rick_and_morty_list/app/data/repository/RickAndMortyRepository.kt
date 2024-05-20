package com.pnow.rick_and_morty_list.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pnow.rick_and_morty_list.app.data.networking.ApiService
import com.pnow.rick_and_morty_list.app.data.networking.CharactersPagingDataSource
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(private val apiService: ApiService, private val pagingDataSource: CharactersPagingDataSource) {

    fun getCharacters(): Flow<PagingData<CharacterUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { pagingDataSource }
        ).flow
    }

    suspend fun getEpisode(episodeId: String) = apiService.getEpisode(episodeId)

    suspend fun getLocation(episodeId: String) = apiService.getLocation(episodeId)

}