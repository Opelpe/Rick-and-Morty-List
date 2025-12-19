package com.pnow.data.location

import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): LocationDTO
}
