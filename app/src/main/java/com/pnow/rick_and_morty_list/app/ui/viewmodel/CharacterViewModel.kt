package com.pnow.rick_and_morty_list.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pnow.rick_and_morty_list.app.data.repository.RickAndMortyRepository
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    rickAndMortyRepository: RickAndMortyRepository,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _charactersState =
        MutableStateFlow<PagingData<CharacterUIModel>>(PagingData.empty())
    val charactersState: StateFlow<PagingData<CharacterUIModel>> = _charactersState

    init {
        viewModelScope.launch(dispatcher) {
            rickAndMortyRepository.getCharacters()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _charactersState.value = pagingData
                }
        }
    }
}
