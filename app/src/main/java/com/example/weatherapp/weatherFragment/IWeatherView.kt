package com.example.weatherapp.weatherFragment

import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData

interface IWeatherView {
  fun updateMainContainer(weatherData: WeatherData)
  fun updateList(listViewData: ListViewData)
  fun showErrorMessage(messageResource: Int)
}