package no.nrk.yr.yrtestkmmregular.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationsDto(
        val location: List<LocationDto>
)