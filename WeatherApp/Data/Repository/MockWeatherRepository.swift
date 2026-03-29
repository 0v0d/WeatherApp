//
//  MockWeatherRepository.swift
//  WeatherApp
//

import Foundation

final class MockWeatherRepository: WeatherRepositoryProtocol, Sendable {
    
    func fetchWeather(for city: String) async throws -> Weather {
        try await Task.sleep(nanoseconds: 500_000_000)
        return Self.createMockWeather(for: city)
    }
    
    nonisolated static func createMockWeather(for city: String) -> Weather {
        let conditions: [WeatherCondition] = [.clear, .clouds, .rain, .snow]
        let randomCondition = conditions.randomElement() ?? .clear
        
        let descriptions: [WeatherCondition: String] = [
            .clear: "clear sky",
            .clouds: "scattered clouds",
            .rain: "light rain",
            .snow: "light snow"
        ]
        
        return Weather(
            cityName: city,
            temperature: Double.random(in: -5...35),
            feelsLike: Double.random(in: -5...35),
            humidity: Int.random(in: 30...90),
            windSpeed: Double.random(in: 0...15),
            condition: randomCondition,
            description: descriptions[randomCondition] ?? "unknown"
        )
    }
}

extension MockWeatherRepository {
    static let sampleWeather = Weather(
        cityName: "Tokyo",
        temperature: 22.5,
        feelsLike: 21.0,
        humidity: 65,
        windSpeed: 3.5,
        condition: .clear,
        description: "clear sky"
    )
    
    static let rainyWeather = Weather(
        cityName: "London",
        temperature: 15.0,
        feelsLike: 13.5,
        humidity: 85,
        windSpeed: 5.2,
        condition: .rain,
        description: "moderate rain"
    )
    
    static let snowyWeather = Weather(
        cityName: "Moscow",
        temperature: -5.0,
        feelsLike: -10.0,
        humidity: 90,
        windSpeed: 8.0,
        condition: .snow,
        description: "heavy snow"
    )
}
