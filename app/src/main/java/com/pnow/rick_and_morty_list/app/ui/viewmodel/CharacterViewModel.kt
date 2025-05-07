package com.pnow.rick_and_morty_list.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pnow.rick_and_morty_list.app.data.repository.RickAndMortyRepository
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(rickAndMortyRepository: RickAndMortyRepository) : ViewModel() {

    val charactersState: Flow<PagingData<CharacterUIModel>> =
        rickAndMortyRepository.getCharacters()
            .cachedIn(viewModelScope)
}
