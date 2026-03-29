//
//  WeatherRepository.swift
//  WeatherApp
//

import Foundation

final class WeatherRepository: WeatherRepositoryProtocol, Sendable {
    private let apiClient: any WeatherAPIClientProtocol

    init(apiClient: any WeatherAPIClientProtocol) {
        self.apiClient = apiClient
    }

    func fetchWeather(for city: String) async throws -> Weather {
        let response = try await apiClient.fetchWeather(for: city)
        return response.toWeather()
    }
}
