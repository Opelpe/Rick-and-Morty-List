package com.pnow.ramlist.app.data.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pnow.ramlist.app.data.model.character.CharacterModel
import javax.inject.Inject

class CharactersPagingDataSource
    @Inject
    constructor(
        private val service: ApiService,
    ) :
    PagingSource<Int, CharacterModel>() {
        override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
            val page = params.key ?: 1
            return try {
                val response = service.getCharacters(page)
                val data = response.results
                LoadResult.Page(
                    data = data,
                    prevKey = if (page <= 1) null else page.minus(1),
                    nextKey = if (data.isEmpty()) null else page.plus(1),
                )
            } catch (exception: Exception) {
                return LoadResult.Error(exception)
            }
        }
    }
