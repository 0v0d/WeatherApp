package com.example.weatherapp.weatherFragment.data

data class WeatherData(
  val location: String,
  val description: String,
  val temp: String,
  val main: String,
  val tempMax: String,
  val tempMin: String,
  val sunrise: String,
  val sunset: String,
  val windSpeed: String,
  val pressure: String,
  val humidity: String,
  val forecastTime: String,
  val formattedDate: String,
  val dayOfWeek: String,
  val formattedTime: String,
  val icon:String,
)