package com.pnow.data.location

import com.pnow.domain.repository.LocationRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl @Inject constructor(private val api: LocationApi) : LocationRepository {
    override fun getLocation(locationId: String) = flow {
        val locationDto = api.getLocation(locationId)
        emit(locationDto.toDomain())
    }
}
