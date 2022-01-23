package no.nrk.yr.yrtestkmmregular.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(

    var id: String,
    val name: String,
    val links: LinksDto? = null


)
