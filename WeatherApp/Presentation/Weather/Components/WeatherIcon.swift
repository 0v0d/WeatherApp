//
//  WeatherIcon.swift
//  WeatherApp
//

import SwiftUI

struct WeatherIcon: View {
    let condition: WeatherCondition
    let size: CGFloat
    
    init(condition: WeatherCondition, size: CGFloat = 80) {
        self.condition = condition
        self.size = size
    }
    
    var body: some View {
        Image(systemName: condition.iconName)
            .font(.system(size: size))
            .symbolRenderingMode(.multicolor)
    }
}

#Preview {
    VStack(spacing: 20) {
        WeatherIcon(condition: .clear)
        WeatherIcon(condition: .rain)
        WeatherIcon(condition: .snow)
        WeatherIcon(condition: .thunderstorm)
    }
}
