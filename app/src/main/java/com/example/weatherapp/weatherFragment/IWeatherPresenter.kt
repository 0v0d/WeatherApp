package com.example.weatherapp.weatherFragment

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.weatherapp.weatherFragment.data.WeatherData

interface IWeatherPresenter {
  fun setLocation(location: String)
  fun getWeatherData(lifecycle: LifecycleCoroutineScope)
  fun reload()
  fun getItemClick(position: Int): WeatherData
  fun getCloudValue(description :String): Int
  fun getRainValue(description :String): Int
  fun onDestroy()
}