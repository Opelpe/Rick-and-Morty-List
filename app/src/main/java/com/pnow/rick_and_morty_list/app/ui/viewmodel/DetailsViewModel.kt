package com.pnow.rick_and_morty_list.app.ui.viewmodel

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

    private val _detailsState = MutableStateFlow<DetailsUIModel?>(null)
    val detailsState: StateFlow<DetailsUIModel?> = _detailsState.asStateFlow()

    fun fetchDetails(episodeUrls: List<String>?, locationUrl: String?, originUrl: String?) {
        viewModelScope.launch(dispatcher) {
            val episodes = mutableListOf<EpisodeUIModel>()

            val originFlow = rickAndMortyRepository.getLocation(getUriPath(originUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }
            val locationFlow = rickAndMortyRepository.getLocation(getUriPath(locationUrl))
                .map { detailsMapper.mapToLocationUiModel(it) }

            val origin = originFlow.first()
            val location = locationFlow.first()

            _detailsState.emit(DetailsUIModel(origin, location, emptyList()))

            episodeUrls.orEmpty().forEach { url ->
                rickAndMortyRepository.getEpisode(getUriPath(url))
                    .map { detailsMapper.mapToEpisodeUIModel(it) }
                    .collect { episode ->
                        if (!episodes.contains(episode)) {
                            episodes.add(episode)
                            _detailsState.emit(
                                DetailsUIModel(origin, location, episodes.toList())
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
