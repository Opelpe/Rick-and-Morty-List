package com.pnow.rick_and_morty_list.app.data.mapper

import android.content.Context
import com.pnow.rick_and_morty_list.R
import com.pnow.rick_and_morty_list.app.data.model.EpisodeModel
import com.pnow.rick_and_morty_list.app.data.model.LocationModel
import com.pnow.rick_and_morty_list.app.data.model.character.CharacterLocationModel
import com.pnow.rick_and_morty_list.app.ui.model.EpisodeUIModel
import com.pnow.rick_and_morty_list.app.ui.model.LocationUIModel
import javax.inject.Inject

class DetailsUiMapper @Inject constructor(val context: Context) {

    private val unknownTitle = context.getString(R.string.common_word_unknown)
    fun mapToLocationUiModel(locationDto: LocationModel?): LocationUIModel {
        val name = capitalize(locationDto?.name?.ifEmpty { unknownTitle } ?: unknownTitle)
        val type = capitalize(locationDto?.type?.ifEmpty { unknownTitle } ?: unknownTitle)
        val dimension = capitalize(locationDto?.dimension?.ifEmpty { unknownTitle } ?: unknownTitle)
        return LocationUIModel(name, type, dimension)
    }

    fun mapToLocationUiModel(locationDto: CharacterLocationModel?): LocationUIModel {
        val name = capitalize(locationDto?.name?.ifEmpty { unknownTitle } ?: unknownTitle)
        val type = unknownTitle
        val dimension = unknownTitle
        return LocationUIModel(name, type, dimension)
    }

    fun mapToEpisodeUIModel(episodeDto: EpisodeModel): EpisodeUIModel {
        val episodeNumbering = episodeDto.episode
        val episodeName = episodeDto.name
        val episodeDesc = " \"$episodeName\" - $episodeNumbering "
        return EpisodeUIModel(episodeDesc)
    }

    private fun capitalize(word: String): String {
        return word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}