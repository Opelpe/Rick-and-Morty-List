package com.pnow.data.episode

import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): EpisodeDTO
}
