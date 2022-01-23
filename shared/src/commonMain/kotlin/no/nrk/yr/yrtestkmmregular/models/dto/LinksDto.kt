package no.nrk.yr.yrtestkmmregular.models.dto


@kotlinx.serialization.Serializable
data class LinksDto(
    var self: LinkDto? = null,
    var celestialevents: LinkDto? = null,
    var forecast: LinkDto? = null,
    var notifications: LinkDto? = null,
    var now: LinkDto? = null,
    var radar: LinkDto? = null,
    var extremeforecasts: LinkDto? = null,
    var airqualityforecast: LinkDto? = null,
    var pollen: LinkDto? = null,
    var watertemperatures: LinkDto? = null,
    var tide: LinkDto? = null,
    var auroraforecast: LinkDto? = null,
)