package com.pnow.rick_and_morty_list.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pnow.rick_and_morty_list.app.data.model.character.CharacterModel
import com.pnow.rick_and_morty_list.app.data.networking.ApiService
import com.pnow.rick_and_morty_list.app.data.networking.CharactersPagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val apiService: ApiService,
    private val pagingDataSource: CharactersPagingDataSource
) {

    fun getCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { pagingDataSource }
        ).flow
    }

    fun getEpisode(episodeId: String) = flow {
        emit(apiService.getEpisode(episodeId))
    }

    fun getLocation(episodeId: String) = flow {
        emit(apiService.getLocation(episodeId))
    }

}
