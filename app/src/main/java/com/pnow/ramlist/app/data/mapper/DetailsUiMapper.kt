package com.pnow.ramlist.app.data.mapper

import android.content.Context
import com.pnow.ramlist.R
import com.pnow.ramlist.app.data.model.EpisodeModel
import com.pnow.ramlist.app.data.model.LocationModel
import com.pnow.ramlist.app.data.model.character.CharacterLocationModel
import com.pnow.ramlist.app.ui.model.EpisodeUIModel
import com.pnow.ramlist.app.ui.model.LocationUIModel
import javax.inject.Inject

class DetailsUiMapper
    @Inject
    constructor(val context: Context) {
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
            return EpisodeUIModel(
                id = episodeDto.id,
                episodeNumber = episodeDto.episode,
                name = episodeDto.name,
                date = episodeDto.airDate,
            )
        }

        private fun capitalize(word: String): String {
            return word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
