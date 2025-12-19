package com.pnow.data.location

import com.pnow.domain.repository.LocationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationApi,
    private val mapper: LocationDTOMapper
) : LocationRepository {
    override fun getLocation(locationId: String) =
        flow {
            val locationDto = api.getLocation(locationId)
            emit(mapper.map(locationDto))
        }
}
