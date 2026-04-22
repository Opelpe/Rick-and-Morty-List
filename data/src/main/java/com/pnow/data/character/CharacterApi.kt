package com.pnow.data.character

import com.pnow.data.character.model.CharacterDTO
import com.pnow.data.character.model.CharacterListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharacterListDTO

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int,
    ): CharacterDTO
}
