package com.pnow.rick_and_morty_list.app.ui.viewmodel

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnow.rick_and_morty_list.app.data.mapper.DetailsUiMapper
import com.pnow.rick_and_morty_list.app.data.model.character.CharacterLocationModel
import com.pnow.rick_and_morty_list.app.data.repository.RickAndMortyRepository
import com.pnow.rick_and_morty_list.app.ui.model.DetailsUIModel
import com.pnow.rick_and_morty_list.app.ui.model.EpisodeUIModel
import com.pnow.rick_and_morty_list.app.ui.model.LocationUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository,
    private val detailsMapper: DetailsUiMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val detailsState: StateFlow<DetailsUiState> = _detailsState.asStateFlow()

    companion object {
        const val TAG = "DetailsVM"
    }

    fun fetchDetails(episodeUrls: List<String>?, locationUrl: String?, originUrl: String?) {
        viewModelScope.launch(dispatcher) {
            _detailsState.emit(DetailsUiState.Loading)

            val episodes = mutableListOf<EpisodeUIModel>()

            val originFlow = rickAndMortyRepository.getLocation(getUriPath(originUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }
            val locationFlow = rickAndMortyRepository.getLocation(getUriPath(locationUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }

            val origin = originFlow.first()
            val location = locationFlow.first()
            val detailsData = DetailsUIModel(
                origin,
                location,
                emptyList()
            )

            _detailsState.emit(DetailsUiState.Success(detailsData))

            episodeUrls.orEmpty().forEach { url ->
                rickAndMortyRepository.getEpisode(getUriPath(url))
                    .map { detailsMapper.mapToEpisodeUIModel(it) }
                    .catch {
                        _detailsState.emit(
                            DetailsUiState.Failure(
                                "Something goes wrong, retry!"
                            )
                        )
                        Log.e(TAG, "Error while getting episodes", it)
                    }
                    .collect { episode ->
                        if (!episodes.contains(episode)) {
                            episodes.add(episode)
                            _detailsState.emit(
                                DetailsUiState.Success(
                                    detailsData.copy(episodeModel = episodes.toList())
                                )
                            )
                        }
                    }
            }
        }
    }

    private fun getUriPath(url: String?): String {
        return url?.toUri()?.lastPathSegment ?: ""
    }

    fun getLocationDescription(model: CharacterLocationModel?): LocationUIModel {
        return detailsMapper.mapToLocationUiModel(model)
    }
}

sealed class DetailsUiState {
    data object Loading : DetailsUiState()
    data class Success(val data: DetailsUIModel) : DetailsUiState()
    data class Failure(val error: String) : DetailsUiState()
}
