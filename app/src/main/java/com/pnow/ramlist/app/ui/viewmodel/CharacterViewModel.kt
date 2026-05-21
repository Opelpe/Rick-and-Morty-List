package com.pnow.ramlist.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pnow.domain.repository.CharacterRepository
import com.pnow.ramlist.app.data.mapper.CharacterMapper
import com.pnow.ramlist.app.ui.model.CharacterInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@HiltViewModel
class CharacterViewModel
@Inject
constructor(
    dispatcher: CoroutineDispatcher,
    mapper: CharacterMapper,
    characterRepository: CharacterRepository,
) : ViewModel() {

    val charactersState: Flow<PagingData<CharacterInfo>> =
        characterRepository.getCharacters()
            .map(mapper::map)
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
}
