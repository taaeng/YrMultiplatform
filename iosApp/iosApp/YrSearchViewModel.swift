
import Foundation
import shared

class YrSearchViewModel : ObservableObject {
    
    @Published var searchResultPublished: SearchResultBusinessObject = SearchResultBusinessObject.Loading()
    
    private let repository: YrRepository

    init(repository: YrRepository) {
        self.repository = repository
        repository.searchResultFlow.watch{ searchResult in
            if (searchResult != nil) {
                self.searchResultPublished = searchResult!
            }
        }
    }
    
    func intent(intent: SearchIntent){
        repository.intent(intent: intent)
    }
    
    
    
}
