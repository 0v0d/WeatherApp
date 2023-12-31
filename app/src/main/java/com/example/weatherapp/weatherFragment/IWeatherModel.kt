package com.example.weatherapp.weatherFragment

import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData

interface IWeatherModel {
  fun getItemClick(position: Int, listData: List<WeatherData>): WeatherData
  fun getListViewData(list: List<WeatherData>): ListViewData
  fun getCloudValue(weather: String): Int
  fun getRainValue(weather: String): Int
}