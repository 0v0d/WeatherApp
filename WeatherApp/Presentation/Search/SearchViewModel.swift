//
//  SearchViewModel.swift
//  WeatherApp
//

import Foundation
import Observation

@Observable
@MainActor
final class SearchViewModel {
    var searchText = ""
    private(set) var searchHistory: [String] = []
    private(set) var isSearching = false
    
    @ObservationIgnored
    private let userDefaults: UserDefaultsClient
    
    private let historyKey = "searchHistory"
    private let maxHistoryCount = 10
    
    init(userDefaults: UserDefaultsClient = .live) {
        self.userDefaults = userDefaults
        loadSearchHistory()
    }
    
    func addToHistory(_ city: String) {
        let trimmed = city.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !trimmed.isEmpty else { return }
        
        var history = searchHistory
        history.removeAll { $0.lowercased() == trimmed.lowercased() }
        history.insert(trimmed, at: 0)
        
        if history.count > maxHistoryCount {
            history = Array(history.prefix(maxHistoryCount))
        }
        
        searchHistory = history
        saveSearchHistory()
    }
    
    func removeFromHistory(_ city: String) {
        searchHistory.removeAll { $0 == city }
        saveSearchHistory()
    }
    
    func clearHistory() {
        searchHistory = []
        saveSearchHistory()
    }
    
    private func loadSearchHistory() {
        if let history = userDefaults.stringArray(historyKey) {
            searchHistory = history
        }
    }
    
    private func saveSearchHistory() {
        userDefaults.setStringArray(searchHistory, historyKey)
    }
}
