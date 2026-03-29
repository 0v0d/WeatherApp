//
//  WeatherResponse.swift
//  WeatherApp
//

import Foundation

struct WeatherResponse: Decodable, Sendable {
    let coord: Coord
    let weather: [WeatherInfo]
    let main: Main
    let wind: Wind
    let name: String
    let dt: Int
    
    struct Coord: Decodable, Sendable {
        let lon: Double
        let lat: Double
    }
    
    struct WeatherInfo: Decodable, Sendable {
        let id: Int
        let main: String
        let description: String
        let icon: String
    }
    
    struct Main: Decodable, Sendable {
        let temp: Double
        let feelsLike: Double
        let humidity: Int
        
        enum CodingKeys: String, CodingKey {
            case temp
            case feelsLike = "feels_like"
            case humidity
        }
    }
    
    struct Wind: Decodable, Sendable {
        let speed: Double
    }
}

extension WeatherResponse {
    nonisolated func toWeather() -> Weather {
        let condition = weather.first.map { WeatherCondition(from: $0.main) } ?? .unknown
        let description = weather.first?.description ?? ""
        
        return Weather(
            cityName: name,
            temperature: main.temp,
            feelsLike: main.feelsLike,
            humidity: main.humidity,
            windSpeed: wind.speed,
            condition: condition,
            description: description,
            timestamp: Date(timeIntervalSince1970: TimeInterval(dt))
        )
    }
}
