package com.pnow.data.character

import com.pnow.data.character.model.CharacterListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterListDTO
}
