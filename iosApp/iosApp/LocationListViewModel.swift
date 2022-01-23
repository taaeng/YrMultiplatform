
import Foundation
import shared

class LocationListViewModel : ObservableObject {
    
    @Published var searchResultPublished: SearchResult = SearchResult.Loading()
    
    private let repository: YrRepository

    init(repository: YrRepository) {
        self.repository = repository
        repository.searchResultFlow.watch{ searchResult in
            if (searchResult != nil) {
                self.searchResultPublished = searchResult!
            }
        }
    }
    
    func searchQuery(query: String) {
        repository.searchFor(query: query)
    }
    
}
