//
//  WeatherViewModel.swift
//  WeatherApp
//

import Foundation
import Observation

@Observable
@MainActor
final class WeatherViewModel {
    private(set) var weather: Weather?
    private(set) var isLoading = false
    private(set) var errorMessage: String?
    
    @ObservationIgnored
    private let weatherRepository: WeatherRepositoryClient
    
    init(weatherRepository: WeatherRepositoryClient = .live) {
        self.weatherRepository = weatherRepository
    }
    
    func fetchWeather(for city: String) async {
        guard !city.isEmpty else { return }
        
        isLoading = true
        errorMessage = nil
        
        do {
            defer{
                isLoading = false
            }
            weather = try await weatherRepository.fetchWeather(city)
        } catch {
            errorMessage = error.localizedDescription
        }
    }
    
    func clearError() {
        errorMessage = nil
    }
}
