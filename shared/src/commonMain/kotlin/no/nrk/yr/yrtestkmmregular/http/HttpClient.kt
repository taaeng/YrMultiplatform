package no.nrk.yr.yrtestkmmregular.http

import io.ktor.client.engine.*

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect fun httpClient(): HttpClientEngineFactory<HttpClientEngineConfig>