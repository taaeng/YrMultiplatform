package no.nrk.yr.yrtestkmmregular.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
        var _embedded: LocationsDto? = null,
)
