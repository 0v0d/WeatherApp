package com.example.weatherapp.weatherFragment.presenter

import com.example.weatherapp.weatherFragment.IWeatherPresenter
import com.example.weatherapp.weatherFragment.IWeatherView

class WeatherPresenterFactory {
  //WeatherPresenterを生成
  fun create(weatherView: IWeatherView): IWeatherPresenter {
    return WeatherPresenter(weatherView)
  }
}