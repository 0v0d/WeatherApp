//
//  WeatherRepositoryProtocol.swift
//  WeatherApp
//

import Foundation

protocol WeatherRepositoryProtocol: Sendable {
    func fetchWeather(for city: String) async throws -> Weather
}
