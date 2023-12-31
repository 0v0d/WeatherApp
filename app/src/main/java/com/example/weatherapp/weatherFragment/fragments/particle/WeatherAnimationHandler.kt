package com.example.weatherapp.weatherFragment.fragments.particle

import android.content.Context
import com.example.weatherapp.databinding.FragmentWeatherBinding

class WeatherAnimationHandler {
  private lateinit var weatherAnimations: WeatherAnimation

  //onViewCreatedでWeatherAnimationsを生成
  fun onViewCreated(context: Context, binding: FragmentWeatherBinding) {
    weatherAnimations = WeatherAnimation(context, binding)
    startAnimation()
  }

  //onCreateViewでCloudViewとRainViewを生成
  fun initAnimation(cloudValue: Int, rainValue: Int) {
    weatherAnimations.setCloudValue(cloudValue)
    weatherAnimations.setRainValue(rainValue)
  }

  //onDestroyでWeatherAnimationsを破棄
  fun onDestroy() {
    weatherAnimations.onDestroy()
  }

  //onCreateViewでWeatherAnimationsを生成
  private fun startAnimation() {
    weatherAnimations.onCreateView()
  }
}