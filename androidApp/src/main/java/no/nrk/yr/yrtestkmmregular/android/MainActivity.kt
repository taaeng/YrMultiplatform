package no.nrk.yr.yrtestkmmregular.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import no.nrk.yr.yrtestkmmregular.YrRepository
import no.nrk.yr.yrtestkmmregular.models.bo.SearchIntent
import no.nrk.yr.yrtestkmmregular.models.bo.SearchResultBusinessObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = YrRepository()
        val tv: TextView = findViewById(R.id.text_view)
        repo.intent(SearchIntent.SearchQuery("Oslosdfsdf"))
        lifecycleScope.launch {
            repo.searchResultFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { searchResult ->
                    tv.text = when(searchResult){
                        is SearchResultBusinessObject.Failed -> "Error: " + searchResult.msg
                        SearchResultBusinessObject.Loading -> "Loading"
                        is SearchResultBusinessObject.Success -> {
                            var result = "Data:"
                            searchResult.items.forEach {
                                result += "*${it.id}: ${it.name}\n"
                            }
                            result
                        }
                    }
                }
        }
    }
}
