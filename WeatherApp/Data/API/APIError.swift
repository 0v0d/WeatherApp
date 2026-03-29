//
//  APIError.swift
//  WeatherApp
//

import Foundation

enum APIError: LocalizedError {
    case invalidURL
    case networkError(Error)
    case invalidResponse
    case decodingError(Error)
    case serverError(statusCode: Int)
    case cityNotFound
    case rateLimitExceeded
    

    var errorDescription: String? {
        switch self {
        case .invalidURL:
            return "Invalid URL"
        case .networkError(let error):
            return "Network error: \(error.localizedDescription)"
        case .invalidResponse:
            return "Invalid response from server"
        case .decodingError(let error):
            return "Failed to decode response: \(error.localizedDescription)"
        case .serverError(let statusCode):
            return "Server error with status code: \(statusCode)"
        case .cityNotFound:
            return "City not found"
        case .rateLimitExceeded:
            return "Rate limit exceeded"
        }
    }
}
