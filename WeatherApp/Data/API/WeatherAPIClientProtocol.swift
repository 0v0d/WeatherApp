//
//  WeatherAPIClientProtocol.swift
//  WeatherApp
//

import Foundation

protocol WeatherAPIClientProtocol: Sendable {
    func fetchWeather(for city: String) async throws -> WeatherResponse
    func fetchWeather(latitude: Double, longitude: Double) async throws -> WeatherResponse
}
