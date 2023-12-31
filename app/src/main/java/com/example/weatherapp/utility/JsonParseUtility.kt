package com.example.weatherapp.utility

import com.example.weatherapp.weatherFragment.data.WeatherData
import org.json.JSONObject
import kotlin.math.roundToInt

//JSONデータをパースするクラス
class JsonParseUtility {
  //現在の天気のデータをパースする
  fun parseCurrentWeatherData(obj: String): WeatherData {
    val jsonObj = JSONObject(obj)
    val location = jsonObj.getString("name")
    val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
    val mainDescription = weather.getString("main")
    val weatherDescription = weather.getString("description")
    val main = jsonObj.getJSONObject("main")
    val temp = main.getDouble("temp").roundToInt().toString()
    val tempMax = main.getDouble("temp_max").roundToInt().toString()
    val tempMin = main.getInt("temp_min").toString()
    val windSpeed = jsonObj.getJSONObject("wind").getString("speed")
    val pressure = main.getString("pressure")
    val humidity = main.getString("humidity")
    val sys = jsonObj.getJSONObject("sys")

    val utility = DateTimeUtility()
    val sunriseTime = sys.getLong("sunrise") + jsonObj.getLong("timezone")
    val sunrise = utility.timestampToTime(sunriseTime)

    val sunsetTime = sys.getLong("sunset") + jsonObj.getLong("timezone")
    val sunset = utility.timestampToTime(sunsetTime)
    val timestamp = jsonObj.getLong("dt") + jsonObj.getLong("timezone")
    val time = utility.timestampToDate(timestamp) + " " +
        utility.timestampToTime(timestamp)
    val icon = weather.getString("icon")
    return WeatherData(
      location, weatherDescription, "$temp℃", mainDescription, "$tempMax℃",
      "$tempMin℃", sunrise, sunset, "$windSpeed m/s",
      "$pressure hPa", "$humidity%", time,
      "-", "-", "-",icon
    )
  }

  //週間天気予報のデータをパースする
  fun parseWeeklyWeatherData(obj: String): List<WeatherData> {
    val jsonObj = JSONObject(obj)
    val weatherDataList = mutableListOf<WeatherData>()
    val size = jsonObj.getJSONArray("list").length() / 2
    for (i in 0 until size) {
      val forecast = jsonObj.getJSONArray("list").getJSONObject(i)
      val mainJSONObj = forecast.getJSONObject("main")

      val city = jsonObj.getJSONObject("city")
      val weather = forecast.getJSONArray("weather")

      val utility = DateTimeUtility()
      val time = forecast.getLong("dt") + city.getLong("timezone")
      val formattedTime = utility.timestampToTime(time)

      val days = utility.timestampToYearDate(time)
      val formattedDate = utility.formatDate(days)
      val dayOfWeek = utility.calculateDayOfWeek(days)

      val location = city.getString("name")

      val main = weather.getJSONObject(0).getString("main")
      val weatherDescription = weather.getJSONObject(0).getString("description")

      val temp = mainJSONObj.getDouble("temp").roundToInt()
      val tempMax = mainJSONObj.getDouble("temp_max").roundToInt()
      val tempMin = mainJSONObj.getInt("temp_min").toString()

      val windSpeed = forecast.getJSONObject("wind").getDouble("speed").roundToInt()
      val pressure = mainJSONObj.getString("pressure")
      val humidity = mainJSONObj.getString("humidity")

      val forecastTime = "$formattedDate $formattedTime"
      val icon = weather.getJSONObject(0).getString("icon")
      weatherDataList.add(
        WeatherData(
          location, weatherDescription, "$temp℃", main,
          "$tempMax℃", "$tempMin℃", "-", "-",
          "$windSpeed m/s", "$pressure hPa", "$humidity%",
          forecastTime, formattedDate, dayOfWeek, formattedTime,icon
        )
      )
    }
    return weatherDataList
  }
}