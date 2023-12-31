package com.example.weatherapp.weatherFragment.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.weatherapp.weatherFragment.IWeatherView
import com.example.weatherapp.weatherFragment.data.WeatherData

class WeatherPresenterHandler(weatherView: IWeatherView) {
  private val weatherPresenter = WeatherPresenterFactory().create(weatherView)

  //locationを設定
  fun setupWeatherPresenter(location: String?, lifecycleScope: LifecycleCoroutineScope) {
    location?.let {
      weatherPresenter.setLocation(it)
    }
    weatherPresenter.getWeatherData(lifecycleScope)
  }

  //再取得
  fun reload() {
    weatherPresenter.reload()
  }

  //Presenterを破棄
  fun onDestroy() {
    weatherPresenter.onDestroy()
  }

  // 曇りの値を取得
  fun getCloudValue(description: String): Int {
    return weatherPresenter.getCloudValue(description)
  }

  // 雨の値を取得
  fun getRainValue(description: String): Int {
    return weatherPresenter.getRainValue(description)
  }

  // ListViewのアイテムをクリックしたときに表示するデータを得る
  fun getItemClick(position: Int): WeatherData {
    return weatherPresenter.getItemClick(position)
  }
}
