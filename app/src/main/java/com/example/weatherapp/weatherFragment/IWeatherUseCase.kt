package com.example.weatherapp.weatherFragment

import com.example.weatherapp.weatherFragment.data.WeatherData

interface IWeatherUseCase {
  fun setLocation(location: String)
  suspend fun getCurrentWeatherData(): WeatherData?
  suspend fun getWeeklyWeatherData(): List<WeatherData>?
}