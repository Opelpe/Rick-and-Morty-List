package com.pnow.ramlist.app.ui.viewmodel

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnow.ramlist.app.data.mapper.DetailsUiMapper
import com.pnow.ramlist.app.data.model.character.CharacterLocationModel
import com.pnow.ramlist.app.data.repository.RickAndMortyRepository
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.model.EpisodeUIModel
import com.pnow.ramlist.app.ui.model.LocationUIModel
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

    private val _detailsState =
        MutableStateFlow<DetailsUiState>(DetailsUiState.CharacterInfoLoading)
    val detailsState: StateFlow<DetailsUiState> = _detailsState.asStateFlow()

    companion object {
        const val TAG = "DetailsVM"
    }

    fun fetchDetails(episodeUrls: List<String>?, locationUrl: String?, originUrl: String?) {
        viewModelScope.launch(dispatcher) {
            _detailsState.emit(DetailsUiState.CharacterInfoLoading)

            val originFlow = rickAndMortyRepository.getLocation(getUriPath(originUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }
            val locationFlow = rickAndMortyRepository.getLocation(getUriPath(locationUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }

            val origin = originFlow.first()
            val location = locationFlow.first()
            val detailsData = CharacterInfo.Details(
                origin,
                location,
            )
            _detailsState.emit(DetailsUiState.CharacterInfoUpdated(detailsData))
            fetchEpisodes(episodeUrls)
        }
    }

    private fun fetchEpisodes(episodeUrls: List<String>?) {
        val urls = episodeUrls.orEmpty()
        if (urls.isEmpty()) return

        val episodes = mutableSetOf<EpisodeUIModel>()

        viewModelScope.launch(dispatcher) {
            _detailsState.emit(DetailsUiState.EpisodesLoading(true))

            urls.forEach { url ->
                rickAndMortyRepository.getEpisode(getUriPath(url))
                    .map { detailsMapper.mapToEpisodeUIModel(it) }
                    .catch {
                        Log.e(TAG, "Error fetching episode from URL: $url", it)
                        _detailsState.emit(
                            DetailsUiState.Failure("Something goes wrong, retry!")
                        )
                    }
                    .collect { episode ->
                        if (episodes.add(episode)) {
                            _detailsState.emit(DetailsUiState.EpisodesUpdated(episodes.toList()))
                        }

                        if (episodes.size == urls.size) {
                            _detailsState.emit(DetailsUiState.EpisodesLoading(false))
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
    data object CharacterInfoLoading : DetailsUiState()
    data class CharacterInfoUpdated(val info: CharacterInfo.Details) : DetailsUiState()
    data class EpisodesLoading(val isLoading: Boolean) : DetailsUiState()
    data class EpisodesUpdated(val episodes: List<EpisodeUIModel>) : DetailsUiState()
    data class Failure(val error: String) : DetailsUiState()
}
