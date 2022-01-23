package no.nrk.yr.yrtestkmmregular

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import no.nrk.yr.yrtestkmmregular.dispatcher.CFlow
import no.nrk.yr.yrtestkmmregular.dispatcher.wrap
import no.nrk.yr.yrtestkmmregular.http.httpClient
import no.nrk.yr.yrtestkmmregular.models.bo.SearchResult
import no.nrk.yr.yrtestkmmregular.models.dto.SearchDto

class YrRepository {

    private val queryStateFlow = MutableStateFlow("")

    private val client = HttpClient(httpClient()) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    fun searchFor(query: String) {
        queryStateFlow.value = query
    }

    val searchResultFlow: CFlow<SearchResult> = queryStateFlow.flatMapLatest { query ->
        flow {
            emit(SearchResult.Loading)
            if (query.isNotEmpty()) {
                val dto = client.get<SearchDto>("https://www.yr.no/api/v0/locations/search?q=$query")
                emit(map(dto))
            } else {
                emit(SearchResult.Failed("Empty"))
            }
        }
    }.wrap()

    private fun map(dto: SearchDto): SearchResult {
        return SearchResult.Success(items = dto._embedded.location.map {
            SearchResult.Success.ResultItem(id = it.id, name = it.name)
        })
    }

}