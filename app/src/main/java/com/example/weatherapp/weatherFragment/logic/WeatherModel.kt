package com.example.weatherapp.weatherFragment.logic

import com.example.weatherapp.utility.WeatherImageManager
import com.example.weatherapp.utility.WeatherUtility
import com.example.weatherapp.weatherFragment.IWeatherModel
import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData

class WeatherModel : IWeatherModel {
  //ListViewのアイテムをクリックしたときに表示するデータを得る
  override fun getItemClick(position: Int, listData: List<WeatherData>): WeatherData {
    val forecast = listData[position]
    return WeatherData(
      forecast.location,
      forecast.description,
      forecast.temp,
      forecast.main,
      forecast.tempMax,
      forecast.tempMin,
      "-",
      "-",
      forecast.windSpeed,
      forecast.pressure,
      forecast.humidity,
      forecast.forecastTime,
      "-",
      "-",
      "-",
      forecast.icon
    )
  }

  // CustomAdapterに渡すデータを作成する
  override fun getListViewData(list: List<WeatherData>): ListViewData {
    val image = WeatherImageManager()
    val date = mutableListOf<String>()
    val week = mutableListOf<String>()
    val time = mutableListOf<String>()
    val temp = mutableListOf<String>()
    val imageIds = mutableListOf<Int>()
    for (forecast in list) {
      date.add(forecast.formattedDate)
      week.add(forecast.dayOfWeek)
      time.add(forecast.formattedTime)
      imageIds.add(image.getWeatherImage(forecast.main))
      temp.add(forecast.temp)
    }
    return ListViewData(date, week, time, imageIds, temp)
  }

  //曇りの値を取得する
  override fun getCloudValue(weather: String): Int {
    return WeatherUtility().getCloudValue(weather)
  }

  //雨の値を取得する
  override fun getRainValue(weather: String): Int {
    return WeatherUtility().getRainValue(weather)
  }
}