package com.pnow.rick_and_morty_list.app.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository,
    private val detailsMapper: DetailsUiMapper,
    private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val _detailsState = MutableLiveData<DetailsUIModel>()
    val detailsState: LiveData<DetailsUIModel> = _detailsState

    fun fetchDetails(episodesUrlList: List<String>?, locationUrl: String?, originUrl: String?) {
        viewModelScope.launch(dispatcher) {
            if (episodesUrlList != null) {
                val episodesList = mutableListOf<EpisodeUIModel>()
                episodesUrlList.forEach {
                    val episodeDto = rickAndMortyRepository.getEpisode(getUriPath(it))
                    val episode = detailsMapper.mapToEpisodeUIModel(episodeDto)
                    if (!episodesList.contains(episode)) {
                        episodesList.add(episode)
                    }
                }

                val originInfo = detailsMapper.mapToLocationUiModel(rickAndMortyRepository.getLocation(getUriPath(originUrl)))
                val locationInfo = detailsMapper.mapToLocationUiModel(rickAndMortyRepository.getLocation(getUriPath(locationUrl)))

                val detailsUIModel = DetailsUIModel(originInfo, locationInfo, episodesList)
                _detailsState.value = detailsUIModel
            }
        }
    }

    fun getLocationDescription(model: CharacterLocationModel?): LocationUIModel {
        return detailsMapper.mapToLocationUiModel(model)
    }

    private fun getUriPath(locationUrl: String?): String {
        val uri = Uri.parse(locationUrl)
        return uri?.lastPathSegment ?: ""
    }

}