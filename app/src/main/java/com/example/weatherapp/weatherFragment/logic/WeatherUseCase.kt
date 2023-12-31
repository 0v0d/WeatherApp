package com.example.weatherapp.weatherFragment.logic

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.utility.JsonParseUtility
import com.example.weatherapp.weatherFragment.IWeatherUseCase
import com.example.weatherapp.weatherFragment.data.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.net.URL
import java.util.Locale

class WeatherUseCase : IWeatherUseCase {
  //APIキーを設定
  private val apiKey: String = BuildConfig.API_KEY

  //APIのURLを設定
  private val url = "https://api.openweathermap.org/data/2.5/"

  //APIのパラメータを設定
  private var apiParameter = ""

  //JSONをパースするためのクラスを設定
  private val jsonParseUtility = JsonParseUtility()

  //locationを設定
  override fun setLocation(location: String) {
    if(Locale.getDefault() == Locale.JAPAN){
      apiParameter = "?q=${location}&units=metric&lang=ja&appid=${apiKey}"
      return
    }
    apiParameter = "?q=${location}&units=metric&appid=${apiKey}"
  }

  //非同期処理でAPIを呼び出す
  //現在の天気情報のデータを取得する
  override suspend fun getCurrentWeatherData(): WeatherData? {
    //suspendは、非同期処理を行う際に、コードをブロックすることなく、
    //一時停止と再開を可能にする
    val currentWeatherData = getWeatherData("${url}weather${apiParameter}")
    if (currentWeatherData != null) {
      return jsonParseUtility.parseCurrentWeatherData(currentWeatherData)
    }
    return null
  }

  //週間天気予報のデータを取得する
  override suspend fun getWeeklyWeatherData(): List<WeatherData>? {
    val weeklyWeatherData = getWeatherData("${url}forecast${apiParameter}")
    if (weeklyWeatherData != null) {
      return jsonParseUtility.parseWeeklyWeatherData(weeklyWeatherData)
    }
    return null
  }

  //APIを呼び出す
  //withContextは、指定したコンテキストで処理を行う
  //Dispatchers.IOを指定することで、I/O処理を行うようにする
  //APIを呼び出す
  //withContextは、指定したコンテキストで処理を行う
  //Dispatchers.IOを指定することで、I/O処理を行うようにする
  private suspend fun getWeatherData(url: String): String? {
    return try {
      //withTimeoutは、指定した時間内に処理が終わらなかった場合に、
      //キャンセルする
      withTimeout(10000) {
        withContext(Dispatchers.IO) {
          //@withContextで指定したコンテキストで処理を行う
          return@withContext URL(url).readText(Charsets.UTF_8)
        }
      }
    } catch (e: Exception) {
      null
    }
  }
}