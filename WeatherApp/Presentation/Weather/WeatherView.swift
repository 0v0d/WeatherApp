//
//  WeatherView.swift
//  WeatherApp
//

import SwiftUI

struct WeatherView: View {
    @State private var viewModel = WeatherViewModel()
    @State private var showingSearch = false

    var body: some View {
        ZStack {
            WeatherBackground(condition: viewModel.weather?.condition ?? .clear)

            VStack {
                headerBar

                Spacer()

                contentView

                Spacer()
            }
            .padding()
        }
        .sheet(isPresented: $showingSearch) {
            SearchView { city in
                showingSearch = false
                Task {
                    await viewModel.fetchWeather(for: city)
                }
            }
        }
        .task {
            if viewModel.weather == nil {
                await viewModel.fetchWeather(for: "Tokyo")
            }
        }
    }

    private var headerBar: some View {
        HStack {
            Spacer()

            Button {
                showingSearch = true
            } label: {
                Image(systemName: "magnifyingglass")
                    .font(.title2)
                    .foregroundStyle(.white)
            }
        }
        .padding(.horizontal)
    }

    @ViewBuilder
    private var contentView: some View {
        if viewModel.isLoading {
            LoadingView()
        } else if let error = viewModel.errorMessage {
            ErrorView(message: error) {
                viewModel.clearError()
            }
        } else if let weather = viewModel.weather {
            WeatherCard(weather: weather)
        } else {
            Text("Search for a city to see weather")
                .foregroundStyle(.white)
        }
    }
}

#Preview {
    WeatherView()
}
