package no.nrk.yr.yrtestkmmregular.http

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*

internal actual fun httpClient(): HttpClientEngineFactory<HttpClientEngineConfig> = Android