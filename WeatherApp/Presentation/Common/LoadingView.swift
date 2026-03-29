//
//  LoadingView.swift
//  WeatherApp
//

import SwiftUI

struct LoadingView: View {
    var body: some View {
        VStack(spacing: 16) {
            ProgressView()
                .progressViewStyle(.circular)
                .scaleEffect(1.5)
                .tint(.white)
            
            Text("Loading...")
                .font(.subheadline)
                .foregroundStyle(.white)
        }
        .padding(40)
        .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: 16))
    }
}

#Preview {
    ZStack {
        Color.blue.ignoresSafeArea()
        LoadingView()
    }
}
