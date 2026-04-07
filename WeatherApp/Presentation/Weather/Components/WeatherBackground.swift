//
//  WeatherBackground.swift
//  WeatherApp
//

import SwiftUI

struct WeatherBackground: View {
    let condition: WeatherCondition

    var body: some View {
        LinearGradient(
            colors: Color.weatherGradient(for: condition),
            startPoint: .topLeading,
            endPoint: .bottomTrailing
        )
        .ignoresSafeArea()
        .animation(.easeInOut(duration: 0.5), value: condition)
    }
}

#Preview {
    WeatherBackground(condition: .clear)
}
