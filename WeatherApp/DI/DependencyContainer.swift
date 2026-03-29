//
//  DependencyContainer.swift
//  WeatherApp

import Foundation

// MARK: - WeatherRepository Client

struct WeatherRepositoryClient: Sendable {
    var fetchWeather: @Sendable (String) async throws -> Weather
    
    static let live = WeatherRepositoryClient(
        fetchWeather: { city in
            let apiClient = WeatherAPIClient()
            let response = try await apiClient.fetchWeather(for: city)
            return response.toWeather()
        }
    )
    
    static let mock = WeatherRepositoryClient(
        fetchWeather: { city in
            try await Task.sleep(nanoseconds: 500_000_000)
            return MockWeatherRepository.createMockWeather(for: city)
        }
    )
}

// MARK: - UserDefaults Client

struct UserDefaultsClient: Sendable {
    var stringArray: @Sendable (String) -> [String]?
    var setStringArray: @Sendable ([String], String) -> Void
    
    static let live = UserDefaultsClient(
        stringArray: { key in
            UserDefaults.standard.stringArray(forKey: key)
        },
        setStringArray: { value, key in
            UserDefaults.standard.set(value, forKey: key)
        }
    )
}
