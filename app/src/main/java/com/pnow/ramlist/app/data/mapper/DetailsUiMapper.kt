package com.pnow.ramlist.app.data.mapper

import android.content.Context
import com.pnow.domain.model.CharacterLocation
import com.pnow.domain.model.Episode
import com.pnow.domain.model.Location
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.EpisodeUIModel
import com.pnow.ramlist.app.ui.model.LocationUIModel
import javax.inject.Inject

class DetailsUiMapper
    @Inject
    constructor(context: Context) {
        private val unknownTitle = context.getString(R.string.common_word_unknown)

        fun mapToLocationUiModel(locationDto: Location?): LocationUIModel {
            val name = capitalize(locationDto?.name?.ifEmpty { unknownTitle } ?: unknownTitle)
            val type = capitalize(locationDto?.type?.ifEmpty { unknownTitle } ?: unknownTitle)
            val dimension = capitalize(locationDto?.dimension?.ifEmpty { unknownTitle } ?: unknownTitle)
            return LocationUIModel(name, type, dimension)
        }

        fun mapToLocationUiModel(locationDto: CharacterLocation?): LocationUIModel {
            val name = capitalize(locationDto?.name?.ifEmpty { unknownTitle } ?: unknownTitle)
            val type = unknownTitle
            val dimension = unknownTitle
            return LocationUIModel(name, type, dimension)
        }

        fun mapToEpisodeUIModel(episodeDto: Episode): EpisodeUIModel {
            return EpisodeUIModel(
                id = episodeDto.id,
                episodeNumber = episodeDto.episode.orEmpty(),
                name = episodeDto.name.orEmpty(),
                date = episodeDto.airDate.orEmpty(),
            )
        }

        private fun capitalize(word: String): String {
            return word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
