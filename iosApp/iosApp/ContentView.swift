import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject  var viewModel = YrSearchViewModel(repository: YrRepository())
    @State private var query: String = ""
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                Text("YR Weather App")
                TextField(
                    "Search...",
                    text: $query
                ).onSubmit {
                    viewModel.intent(intent: SearchIntent.SearchQuery(query: query))
                }.padding(16)
                
                let searchResult = viewModel.searchResultPublished
                if(searchResult is SearchResultBusinessObject.Success){
                    let successResult = searchResult as! SearchResultBusinessObject.Success
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
                } else if(searchResult is SearchResultBusinessObject.Loading){
                    Text("Loading").frame(maxWidth: .infinity, maxHeight: .infinity,alignment: .center)
                } else if(searchResult is SearchResultBusinessObject.Failed){
                    let failedResult = searchResult as! SearchResultBusinessObject.Failed
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
