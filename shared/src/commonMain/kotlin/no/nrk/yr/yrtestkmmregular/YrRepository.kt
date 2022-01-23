package no.nrk.yr.yrtestkmmregular

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import no.nrk.yr.yrtestkmmregular.dispatcher.CFlow
import no.nrk.yr.yrtestkmmregular.dispatcher.wrap
import no.nrk.yr.yrtestkmmregular.http.httpClient
import no.nrk.yr.yrtestkmmregular.models.bo.SearchIntent
import no.nrk.yr.yrtestkmmregular.models.bo.SearchResultBusinessObject
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

    fun intent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.NavigateToLocation -> TODO()
            is SearchIntent.SearchQuery -> queryStateFlow.value = intent.query
        }
    }

    val searchResultFlow: CFlow<SearchResultBusinessObject> =
        queryStateFlow.flatMapLatest { query ->
            flow {
                emit(SearchResultBusinessObject.Loading)
                if (query.isNotEmpty()) {
                    println("CLOWN try")
                    val dto =
                        client.get<SearchDto>("https://www.yr.no/api/v0/locations/search?q=$query")
                    emit(mapToBusinessObject(dto))
                } else {
                    emit(SearchResultBusinessObject.Failed("Empty"))
                }
            }
        }.catch {
            emit(SearchResultBusinessObject.Failed("Failed to connect to network"))
        }.wrap()

    private fun mapToBusinessObject(dto: SearchDto): SearchResultBusinessObject {
        val searchResult = dto._embedded?.location

        return if (searchResult.isNullOrEmpty()) {
            SearchResultBusinessObject.Failed("No result")
        } else {
            SearchResultBusinessObject.Success(items = searchResult.map {
                SearchResultBusinessObject.Success.ResultItem(id = it.id, name = it.name)
            })
        }
    }

}