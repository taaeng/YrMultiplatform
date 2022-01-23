import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject  var viewModel = LocationListViewModel(repository: YrRepository())
    @State private var query: String = ""
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                Text("YR Weather App")
                TextField(
                    "Search...",
                    text: $query
                ).onSubmit {
                    viewModel.searchQuery(query: query)
                }.padding(16)
                
                let searchResult = viewModel.searchResultPublished
                if(searchResult is SearchResult.Success){
                    let successResult = searchResult as! SearchResult.Success
                    ScrollView {
                        VStack(alignment: .leading) {
                            ForEach(successResult.items, id: \.self) { result in
                                HStack {
                                    Text(result.name)
                                        .padding(16)
                                        .font(.title2)
                                    Spacer()
                                }
                            }
                            
                        }.frame(width: geometry.size.width)
                        
                    }
                } else if(searchResult is SearchResult.Loading){
                    Text("Loading").frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .center)
                } else if(searchResult is SearchResult.Failed){
                    let failedResult = searchResult as! SearchResult.Failed
                    Text(failedResult.msg).frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                }
                
            }
        }
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
}
