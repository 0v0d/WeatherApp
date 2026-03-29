//
//  Weather.swift
//  WeatherApp
//

import Foundation

struct Weather: Identifiable, Equatable, Sendable {
    let id: UUID
    let cityName: String
    let temperature: Double
    let feelsLike: Double
    let humidity: Int
    let windSpeed: Double
    let condition: WeatherCondition
    let description: String
    let timestamp: Date
    
    nonisolated init(
        id: UUID = UUID(),
        cityName: String,
        temperature: Double,
        feelsLike: Double,
        humidity: Int,
        windSpeed: Double,
        condition: WeatherCondition,
        description: String,
        timestamp: Date = Date()
    ) {
        self.id = id
        self.cityName = cityName
        self.temperature = temperature
        self.feelsLike = feelsLike
        self.humidity = humidity
        self.windSpeed = windSpeed
        self.condition = condition
        self.description = description
        self.timestamp = timestamp
    }
    
    var temperatureCelsius: String {
        String(format: "%.0f°", temperature)
    }
    
    var feelsLikeCelsius: String {
        String(format: "%.0f°", feelsLike)
    }
    
    var humidityPercent: String {
        "\(humidity)%"
    }
    
    var windSpeedFormatted: String {
        String(format: "%.1f m/s", windSpeed)
    }
}

enum WeatherCondition: String, CaseIterable, Sendable {
    case clear = "Clear"
    case clouds = "Clouds"
    case rain = "Rain"
    case drizzle = "Drizzle"
    case thunderstorm = "Thunderstorm"
    case snow = "Snow"
    case mist = "Mist"
    case fog = "Fog"
    case haze = "Haze"
    case unknown = "Unknown"
    
    var iconName: String {
        switch self {
        case .clear: return "sun.max.fill"
        case .clouds: return "cloud.fill"
        case .rain: return "cloud.rain.fill"
        case .drizzle: return "cloud.drizzle.fill"
        case .thunderstorm: return "cloud.bolt.rain.fill"
        case .snow: return "cloud.snow.fill"
        case .mist, .fog, .haze: return "cloud.fog.fill"
        case .unknown: return "questionmark.circle.fill"
        }
    }
    
    nonisolated init(from string: String) {
        self = WeatherCondition.allCases.first {
            $0.rawValue.lowercased() == string.lowercased()
        } ?? .unknown
    }
}
