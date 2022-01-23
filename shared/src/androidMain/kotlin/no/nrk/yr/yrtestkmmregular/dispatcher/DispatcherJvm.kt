package no.nrk.yr.yrtestkmmregular.dispatcher

import kotlinx.coroutines.*
import java.util.*

internal actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main
