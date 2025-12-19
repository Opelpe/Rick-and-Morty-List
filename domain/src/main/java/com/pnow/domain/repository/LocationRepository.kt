package com.pnow.domain.repository

import com.pnow.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(
        locationId: String
    ): Flow<Location>
}
