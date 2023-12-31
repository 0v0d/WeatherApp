package com.example.weatherapp.weatherFragment.logic

import com.example.weatherapp.weatherFragment.IWeatherUseCase
import com.example.weatherapp.weatherFragment.data.WeatherData

class WeatherDataSource {
  private val weatherUseCase: IWeatherUseCase = WeatherUseCase()
  private var currentWeatherData: WeatherData? = null
  private var weeklyWeatherDataList: List<WeatherData>? = null

  //locationを設定
  fun setLocation(location: String) {
    weatherUseCase.setLocation(location)
  }

  //現在の天気情報のデータを取得する
  suspend fun loadCurrentWeatherData(): WeatherData? {
    return try {
      currentWeatherData ?: weatherUseCase.getCurrentWeatherData().also {
        currentWeatherData = it
      }
    } catch (e: Exception) {
      null
    }
  }

  //週間天気予報のデータを取得する
  suspend fun loadWeeklyWeatherDataList(): List<WeatherData>? {
    return try {
      weeklyWeatherDataList ?: weatherUseCase.getWeeklyWeatherData().also {
        weeklyWeatherDataList = it
      }
    } catch (e: Exception) {
      null
    }
  }

  //現在の天気情報のデータを取得する
  fun getCurrentWeatherData(): WeatherData {
    return currentWeatherData!!
  }

  //週間の天気情報のデータを取得する
  fun getWeeklyWeatherDataList(): List<WeatherData> {
    return weeklyWeatherDataList!!
  }

  //データの破棄
  fun onDestroy() {
    currentWeatherData = null
    weeklyWeatherDataList = null
  }
}