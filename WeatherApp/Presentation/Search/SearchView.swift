//
//  SearchView.swift
//  WeatherApp
//

import SwiftUI

struct SearchView: View {
    @State private var viewModel = SearchViewModel()
    @Environment(\.dismiss) private var dismiss
    
    let onCitySelected: (String) -> Void
    
    init(onCitySelected: @escaping (String) -> Void) {
        self.onCitySelected = onCitySelected
    }
    
    var body: some View {
        NavigationStack {
            List {
                searchSection
                historySection
            }
            .navigationTitle("Search City")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancel") {
                        dismiss()
                    }
                }
            }
        }
    }
    
    private var searchSection: some View {
        Section {
            HStack {
                TextField("Enter city name", text: $viewModel.searchText)
                    .textFieldStyle(.plain)
                    .submitLabel(.search)
                    .onSubmit {
                        submitSearch()
                    }
                
                if !viewModel.searchText.isEmpty {
                    Button {
                        viewModel.searchText = ""
                    } label: {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundStyle(.secondary)
                    }
                    .buttonStyle(.plain)
                }
            }
            
            Button {
                submitSearch()
            } label: {
                HStack {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
                }
            }
            .disabled(viewModel.searchText.trimmingCharacters(in: .whitespaces).isEmpty)
        }
    }
    
    @ViewBuilder
    private var historySection: some View {
        if !viewModel.searchHistory.isEmpty {
            Section {
                ForEach(viewModel.searchHistory, id: \.self) { city in
                    Button {
                        selectCity(city)
                    } label: {
                        HStack {
                            Image(systemName: "clock")
                                .foregroundStyle(.secondary)
                            Text(city)
                                .foregroundStyle(.primary)
                        }
                    }
                }
                .onDelete { indexSet in
                    for index in indexSet {
                        viewModel.removeFromHistory(viewModel.searchHistory[index])
                    }
                }
            } header: {
                HStack {
                    Text("Recent Searches")
                    Spacer()
                    Button("Clear") {
                        viewModel.clearHistory()
                    }
                    .font(.caption)
                }
            }
        }
    }
    
    private func submitSearch() {
        let city = viewModel.searchText.trimmingCharacters(in: .whitespaces)
        guard !city.isEmpty else { return }
        selectCity(city)
    }
    
    private func selectCity(_ city: String) {
        viewModel.addToHistory(city)
        onCitySelected(city)
    }
}

#Preview {
    SearchView { city in
        print("Selected: \(city)")
    }
}
