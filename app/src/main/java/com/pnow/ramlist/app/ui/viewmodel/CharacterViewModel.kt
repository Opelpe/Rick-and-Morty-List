package com.pnow.ramlist.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pnow.domain.repository.CharacterRepository
import com.pnow.ramlist.app.data.mapper.CharacterUiModelMapper
import com.pnow.ramlist.app.ui.model.CharacterInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
    @Inject
    constructor(
        dispatcher: CoroutineDispatcher,
        mapper: CharacterUiModelMapper,
        characterRepository: CharacterRepository,
    ) : ViewModel() {
        private val _charactersState =
            MutableStateFlow<PagingData<CharacterInfo.ListItem>>(PagingData.empty())
        val charactersState: StateFlow<PagingData<CharacterInfo.ListItem>> = _charactersState

        init {
            viewModelScope.launch(dispatcher) {
                characterRepository.getCharacters()
                    .map(mapper::map)
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _charactersState.value = pagingData
                    }
            }
        }
    }
