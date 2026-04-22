package com.pnow.ramlist.app.ui.viewmodel

import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pnow.domain.model.CharacterStatus
import com.pnow.domain.repository.CharacterRepository
import com.pnow.domain.repository.EpisodeRepository
import com.pnow.domain.repository.LocationRepository
import com.pnow.ramlist.app.data.mapper.DetailsMapper
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.model.DetailsInfo
import com.pnow.ramlist.app.ui.navigation.AppDestinations.CHARACTER_ID_KEY
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.app.ui.state.DetailsUiState
import com.pnow.ramlist.app.ui.state.EpisodeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltViewModel
class DetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository,
    private val locationRepository: LocationRepository,
    private val detailsMapper: DetailsMapper,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle[CHARACTER_ID_KEY])

    private val _detailsState = MutableStateFlow(DetailsUiState())
    val detailsState: StateFlow<DetailsUiState> = _detailsState.asStateFlow()

    init {
        loadCharacterDetails()
    }

    private fun loadCharacterDetails() {
        viewModelScope.launch(dispatcher) {
            val character =
                runCatching {
                    characterRepository.getCharacterById(characterId)
                }.getOrElse {
                    _detailsState.update {
                        it.copy(
                            character = CharacterInfoState.Failure(ERROR_LOAD_CHARACTER),
                            episodes = EpisodeState.Success(emptyList()),
                        )
                    }
                    return@launch
                }

            val originDeferred =
                async {
                    locationRepository.getLocation(getUriPath(character.origin.url))
                        .map { detailsMapper.mapToLocationInfo(it) }
                        .first()
                }
            val locationDeferred =
                async {
                    locationRepository.getLocation(getUriPath(character.location.url))
                        .map { detailsMapper.mapToLocationInfo(it) }
                        .first()
                }

            runCatching {
                originDeferred.await() to locationDeferred.await()
            }.onSuccess { (origin, location) ->
                _detailsState.update {
                    it.copy(
                        character =
                        CharacterInfoState.Success(
                            DetailsInfo(
                                character =
                                CharacterInfo(
                                    id = character.id,
                                    name = character.name,
                                    statusDescription = character.status,
                                    status = CharacterStatus.fromString(character.status),
                                    species = character.species,
                                    gender = character.gender,
                                    imageUrl = character.imageUrl,
                                ),
                                origin = origin,
                                location = location,
                            ),
                        ),
                    )
                }
                loadEpisodes(character.episodeUrl)
            }.onFailure {
                _detailsState.update {
                    it.copy(
                        character = CharacterInfoState.Failure(ERROR_LOAD_CHARACTER),
                        episodes = EpisodeState.Success(emptyList()),
                    )
                }
            }
        }
    }

    private fun loadEpisodes(urls: List<String>) {
        if (urls.isEmpty()) {
            _detailsState.update { it.copy(episodes = EpisodeState.Success(emptyList())) }
            return
        }

        viewModelScope.launch(dispatcher) {
            val episodes =
                supervisorScope {
                    urls.map { url ->
                        async {
                            runCatching {
                                episodeRepository.getEpisode(getUriPath(url))
                                    .map { detailsMapper.mapToEpisodeInfo(it) }
                                    .first()
                            }.getOrNull()
                        }
                    }.awaitAll().filterNotNull()
                }

            _detailsState.update {
                it.copy(
                    episodes =
                    if (episodes.isEmpty()) {
                        EpisodeState.Failure(ERROR_LOAD_EPISODES)
                    } else {
                        EpisodeState.Success(episodes)
                    },
                )
            }
        }
    }

    private fun getUriPath(url: String?): String = url?.toUri()?.lastPathSegment ?: ""

    companion object {

        private const val ERROR_LOAD_CHARACTER = "Failed to load character"
        private const val ERROR_LOAD_EPISODES = "Failed to load episodes"
    }
}
