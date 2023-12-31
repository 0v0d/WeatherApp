package com.example.weatherapp.utility

import com.example.weatherapp.R

class WeatherImageManager {
  //天気に応じたアイコンを返す
  fun getWeatherImage(weather: String): Int {
    val image = when (weather) {
      "Ash" -> R.drawable.foggy
      "Clear" -> R.drawable.sun
      "Clouds" -> R.drawable.cloud
      "Drizzle" -> R.drawable.rain
      "Mist" -> R.drawable.foggy
      "Rain" -> R.drawable.rain
      "Sand" -> R.drawable.foggy
      "Smoke" -> R.drawable.foggy
      "Snow" -> R.drawable.snow
      "Squall" -> R.drawable.tornado
      "Thunderstorm" -> R.drawable.thunderstorm
      "Tornado" -> R.drawable.tornado
      else -> R.drawable.foggy
    }
    return image
  }

  //天気に応じた背景画像を返す
  fun getBackGroundImageId(weather: String, isNight: Boolean): Int {
    val image = when {
      isNight -> {
        R.drawable.background_night
      }
      else -> {
        when (weather) {
          "Clear" -> R.drawable.background_clear_sky
          "Clouds" -> R.drawable.background_clear_sky
          else -> R.drawable.background_rain
        }
      }
    }
    return image
  }
}