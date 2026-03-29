//
//  WeatherAPIClient.swift
//  WeatherApp
//

import CryptoKit
import Foundation

final class WeatherAPIClient: Sendable {
    private let baseURL: String
    private let hmacSecret: String

    nonisolated init() {
        guard let urlString = Bundle.main.infoDictionary?["BASE_URL"] as? String,
        !urlString.isEmpty else {
            fatalError("BASE_URL が設定されていません")
        }
        guard let secret = Bundle.main.infoDictionary?["HMAC_SECRET"] as? String,
              !secret.isEmpty else {
            fatalError("HMAC_SECRET が設定されていません")
        }
        self.baseURL = urlString
        self.hmacSecret = secret
    }

    func fetchWeather(for city: String) async throws -> WeatherResponse {
        guard let encodedCity = city.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else {
            throw APIError.invalidURL
        }
        let query = "?city=\(encodedCity)"
        return try await request(query: query)
    }

    func fetchWeather(latitude: Double, longitude: Double) async throws -> WeatherResponse {
        let query = "?lat=\(latitude)&lon=\(longitude)"
        return try await request(query: query)
    }

    private func request(query: String) async throws -> WeatherResponse {
        guard let url = URL(string: baseURL + query) else {
            throw APIError.invalidURL
        }

        var request = URLRequest(url: url)
        request.httpMethod = "GET"

        // 署名ヘッダーを追加
        let headers = generateAuthHeaders(query: query)
        headers.forEach { request.setValue($0.value, forHTTPHeaderField: $0.key) }

        let (data, response) = try await URLSession.shared.data(for: request)

        guard let httpResponse = response as? HTTPURLResponse else {
            throw APIError.invalidResponse
        }

        switch httpResponse.statusCode {
        case 200:
            return try JSONDecoder().decode(WeatherResponse.self, from: data)
        default:
            throw APIError.cityNotFound
        }
    }

    private func generateAuthHeaders(query: String) -> [String: String] {
        let timestamp = String(Int(Date().timeIntervalSince1970 * 1000))
        let nonce = UUID().uuidString
        let message = "\(timestamp):\(nonce):\(query)"
        let signature = hmacSHA256(message: message)

        return [
            "X-Timestamp": timestamp,
            "X-Nonce": nonce,
            "X-Signature": signature
        ]
    }

    private func hmacSHA256(message: String) -> String {
        let key = SymmetricKey(data: Data(hmacSecret.utf8))
        let signature = HMAC<SHA256>.authenticationCode(
            for: Data(message.utf8),
            using: key
        )
        return signature.map { String(format: "%02x", $0) }.joined()
    }
}


