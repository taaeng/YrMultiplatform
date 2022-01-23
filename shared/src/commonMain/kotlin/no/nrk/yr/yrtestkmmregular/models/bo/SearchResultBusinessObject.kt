package no.nrk.yr.yrtestkmmregular.models.bo

sealed class SearchResultBusinessObject(){
    data class Success(
        val items: List<ResultItem>
    ) : SearchResultBusinessObject() {
        data class ResultItem(val id: String, val name: String)
    }
    data class Failed(val msg: String) : SearchResultBusinessObject()
    object Loading: SearchResultBusinessObject()
}

sealed class SearchIntent{
    data class SearchQuery(val query: String) : SearchIntent()
    data class NavigateToLocation(val locationId: String) : SearchIntent()

}