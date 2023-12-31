package com.example.weatherapp.utility

import android.util.Log

class WeatherUtility {
  private val utility = Utility()

  //Cloudの値を返す
  fun getCloudValue(iconID: String): Int {
    val value = when (getWeatherID(iconID)) {
      //few clouds
      "02" -> utility.getRandom(5, 10)
      //scattered clouds
      "03" -> utility.getRandom(15, 25)
      //broken clouds
      "04" -> utility.getRandom(30, 35)
      //heavy rain
      "09" -> utility.getRandom(40, 50)
      //rain
      "10" -> utility.getRandom(40, 50)
      //thunderstorm
      "11" -> utility.getRandom(40, 50)
      //snow
      "13" -> utility.getRandom(40, 50)
      //Mist etc...
      "50" -> utility.getRandom(30, 35)
      else -> 0
    }
    Log.d("CloudValue", value.toString())
    return value
  }

  //Rainの値を返す
  fun getRainValue(iconID: String): Int {
    val value = when (getWeatherID(iconID)) {
      //heavy rain
      "09" -> utility.getRandom(70, 80)
      //rain
      "10" -> utility.getRandom(50, 60)
      //thunderstorm
      "11" -> utility.getRandom(70, 80)
      //snow
      "13" -> utility.getRandom(50, 60)
      else -> 0
    }
    return value
  }

  //WeatherIDの先頭2文字を返す
  private fun getWeatherID(iconID: String): String {
    return iconID.substring(0, 2)
  }
}