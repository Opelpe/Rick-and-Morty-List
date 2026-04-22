package com.pnow.ramlist.app.data.mapper

import android.content.Context
import com.pnow.domain.model.Episode
import com.pnow.domain.model.Location
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.EpisodeInfo
import com.pnow.ramlist.app.ui.model.LocationInfo
import javax.inject.Inject

class DetailsMapper
@Inject
constructor(context: Context) {

    private val unknownTitle = context.getString(R.string.common_word_unknown)

    fun mapToLocationInfo(location: Location): LocationInfo {
        val name = capitalize(location.name?.ifEmpty { unknownTitle } ?: unknownTitle)
        val type = capitalize(location.type?.ifEmpty { unknownTitle } ?: unknownTitle)
        val dimension = capitalize(location.dimension?.ifEmpty { unknownTitle } ?: unknownTitle)
        return LocationInfo(name, type, dimension)
    }

    fun mapToEpisodeInfo(episode: Episode) = with(episode) {
        EpisodeInfo(
            id = id,
            episodeNumber = episodeNumber.orEmpty(),
            name = name.orEmpty(),
            date = airDate.orEmpty(),
        )
    }

    private fun capitalize(word: String): String {
        return word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}
