//
//  WeatherCard.swift
//  WeatherApp
//

import SwiftUI

struct WeatherCard: View {
    let weather: Weather

    var body: some View {
        VStack(spacing: 20) {
            cityHeader
            temperatureSection
            detailsGrid
        }
        .padding(24)
        .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: 24))
    }

    private var cityHeader: some View {
        VStack(spacing: 4) {
            Text(weather.cityName)
                .font(.title)
                .fontWeight(.semibold)
            Text(weather.description.capitalized)
                .font(.subheadline)
                .foregroundStyle(.secondary)
        }
    }

    private var temperatureSection: some View {
        HStack(spacing: 20) {
            WeatherIcon(condition: weather.condition, size: 60)

            VStack(alignment: .leading) {
                Text(weather.temperatureCelsius)
                    .font(.system(size: 64, weight: .thin))
                Text("Feels like \(weather.feelsLikeCelsius)")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
            }
        }
    }

    private var detailsGrid: some View {
        HStack(spacing: 30) {
            WeatherDetailItem(
                icon: "humidity.fill",
                value: weather.humidityPercent,
                label: "Humidity"
            )

            WeatherDetailItem(
                icon: "wind",
                value: weather.windSpeedFormatted,
                label: "Wind"
            )
        }
    }
}

struct WeatherDetailItem: View {
    let icon: String
    let value: String
    let label: String

    var body: some View {
        VStack(spacing: 8) {
            Image(systemName: icon)
                .font(.title2)
                .foregroundStyle(.secondary)
            Text(value)
                .font(.headline)
            Text(label)
                .font(.caption)
                .foregroundStyle(.secondary)
        }
    }
}

#Preview {
    ZStack {
        Color.blue.opacity(0.3).ignoresSafeArea()
        WeatherCard(weather: MockWeatherRepository.sampleWeather)
            .padding()
    }
}
