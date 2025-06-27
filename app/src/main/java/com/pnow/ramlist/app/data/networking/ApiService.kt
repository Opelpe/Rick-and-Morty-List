package com.pnow.ramlist.app.data.networking

import com.pnow.ramlist.app.data.model.EpisodeModel
import com.pnow.ramlist.app.data.model.LocationModel
import com.pnow.ramlist.app.data.networking.model.CharacterResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharacterResponseModel

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String,
    ): EpisodeModel

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String,
    ): LocationModel
}
