package com.pnow.rick_and_morty_list.app.data.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pnow.rick_and_morty_list.app.data.mapper.CharacterUiModelMapper
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(
    private val service: ApiService,
    private val mapper: CharacterUiModelMapper
) :
    PagingSource<Int, CharacterUIModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterUIModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterUIModel> {
        val page = params.key ?: 1
        return try {
            val response = service.getCharacters(page)
            val data = mapper.mapToUIModel(response)
            LoadResult.Page(
                data = data,
                prevKey = if (page <= 1) null else page.minus(1),
                nextKey = if (data.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}
