package no.nrk.yr.yrtestkmmregular.http

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

internal actual fun httpClient(): HttpClientEngineFactory<HttpClientEngineConfig> = Ios