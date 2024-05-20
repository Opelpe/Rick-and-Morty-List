package com.pnow.rick_and_morty_list.app.ui.model


data class DetailsUIModel(
    val originModel: LocationUIModel,
    val locationModel: LocationUIModel,
    val episodeModel: List<EpisodeUIModel>
)