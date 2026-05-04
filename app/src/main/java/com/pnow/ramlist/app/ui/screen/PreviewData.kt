package com.pnow.ramlist.app.ui.screen

import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.model.DetailsInfo
import com.pnow.ramlist.app.ui.model.EpisodeInfo
import com.pnow.ramlist.app.ui.model.LocationInfo

val previewCharacter =
    CharacterInfo(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.ALIVE,
        statusDescription = "Alive",
        species = "Human",
        gender = "Male",
        imageUrl = "",
    )

val previewLocation1 =
    LocationInfo(
        name = "Earth (C-137)",
        type = "Planet",
        dimension = "C-137",
    )
val previewLocation2 =
    LocationInfo(
        name = "Moon of Mars",
        type = "Moooooooooooooooooooooooooooooon",
        dimension = "XY-77777",
    )
val previewEpisode1 =
    EpisodeInfo(
        id = 1,
        name = "Lawnmower Dog",
        episodeNumber = "S01E02",
        date = "December 9, 2013",
    )
val previewEpisode2 =
    EpisodeInfo(
        id = 2,
        name = "Pilot",
        episodeNumber = "S01E01",
        date = "December 2, 2013",
    )

val previewEpisode3 =
    EpisodeInfo(
        id = 3,
        name = "Rick And Morty 09",
        episodeNumber = "S01E02",
        date = "December 25, 2022",
    )

val previewDetails =
    DetailsInfo(
        character = previewCharacter,
        origin = previewLocation1,
        location = previewLocation2,
    )
