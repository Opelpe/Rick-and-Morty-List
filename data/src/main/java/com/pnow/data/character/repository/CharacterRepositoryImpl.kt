package com.pnow.data.character.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pnow.data.character.CharacterApi
import com.pnow.data.character.CharactersPagingDataSource
import com.pnow.domain.model.Character
import com.pnow.domain.repository.CharacterRepository
import javax.inject.Inject
import javax.inject.Provider
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl @Inject constructor(
    private val pagingDataSourceProvider: Provider<CharactersPagingDataSource>,
    private val characterApi: CharacterApi,
) : CharacterRepository {

    override fun getCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE
        ),
        pagingSourceFactory = { pagingDataSourceProvider.get() }
    ).flow

    override suspend fun getCharacterById(id: Int): Character =
        characterApi.getCharacterById(id).toDomain()

    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = 35
    }
}
