package no.nrk.yr.yrtestkmmregular.models.bo

sealed class SearchResult(){
    data class Success(
        val items: List<ResultItem>
    ) : SearchResult() {
        data class ResultItem(val id: String, val name: String)
    }
    data class Failed(val msg: String) : SearchResult()
    object Loading: SearchResult()
}