package com.pnow.data.location

import com.pnow.domain.model.Location
import javax.inject.Inject

class LocationDTOMapper @Inject constructor() {
    fun map(dto: LocationDTO): Location {
        return with(dto) {
            Location(
                id = id,
                name = name,
                type = type,
                dimension = dimension
            )
        }
    }
}
