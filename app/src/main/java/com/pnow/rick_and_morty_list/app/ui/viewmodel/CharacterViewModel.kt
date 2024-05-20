package com.pnow.rick_and_morty_list.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pnow.rick_and_morty_list.app.data.repository.RickAndMortyRepository
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val rickAndMortyRepository: RickAndMortyRepository) : ViewModel() {

    private val _charactersState = MutableLiveData<PagingData<CharacterUIModel>>()
    val charactersState: LiveData<PagingData<CharacterUIModel>> = _charactersState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {

        rickAndMortyRepository.getCharacters().cachedIn(viewModelScope).onEach {
            _charactersState.value = CharacterViewState(it).characters
        }.launchIn(viewModelScope)
    }
}

data class CharacterViewState(
    val characters: PagingData<CharacterUIModel>
)