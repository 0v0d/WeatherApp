//
//  Color+Weather.swift
//  WeatherApp
//

import SwiftUI

extension Color {
    static func weatherGradient(for condition: WeatherCondition) -> [Color] {
        switch condition {
        case .clear:
            return [Color(hex: "87CEEB"), Color(hex: "1E90FF")]
        case .clouds:
            return [Color(hex: "B0C4DE"), Color(hex: "708090")]
        case .rain, .drizzle:
            return [Color(hex: "4682B4"), Color(hex: "2F4F4F")]
        case .thunderstorm:
            return [Color(hex: "483D8B"), Color(hex: "191970")]
        case .snow:
            return [Color(hex: "E0E8FF"), Color(hex: "B0C4DE")]
        case .mist, .fog, .haze:
            return [Color(hex: "C0C0C0"), Color(hex: "808080")]
        case .unknown:
            return [Color(hex: "87CEEB"), Color(hex: "1E90FF")]
        }
    }
    
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        let a, r, g, b: UInt64
        switch hex.count {
        case 3:
            (a, r, g, b) = (255, (int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
        case 6:
            (a, r, g, b) = (255, int >> 16, int >> 8 & 0xFF, int & 0xFF)
        case 8:
            (a, r, g, b) = (int >> 24, int >> 16 & 0xFF, int >> 8 & 0xFF, int & 0xFF)
        default:
            (a, r, g, b) = (255, 0, 0, 0)
        }
        self.init(
            .sRGB,
            red: Double(r) / 255,
            green: Double(g) / 255,
            blue: Double(b) / 255,
            opacity: Double(a) / 255
        )
    }
}
