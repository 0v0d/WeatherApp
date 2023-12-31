package com.example.weatherapp.weatherFragment.fragments.particle

import android.content.Context
import com.example.weatherapp.databinding.FragmentWeatherBinding

class WeatherAnimation(
  private val context: Context,
  private val binding: FragmentWeatherBinding
) {
  private lateinit var cloudView: IAnimationView
  private lateinit var rainView: IAnimationView

  //onCreateViewでCloudViewとRainViewを生成
  fun onCreateView() {
    rainView = RainView(context)
    cloudView = CloudView(context)
    binding.apply {
      cloudViewContainer.addView(cloudView as CloudView)
      rainViewContainer.addView(rainView as RainView)
    }
  }

  //Cloudの値を設定
  fun setCloudValue(cloudValue: Int) {
    cloudView.setValue(cloudValue)
  }

  //Rainの値を設定
  fun setRainValue(rainValue: Int) {
    rainView.setValue(rainValue)
  }

  //onDestroyでCloudViewとRainViewを破棄
  fun onDestroy() {
    cloudView.onDestroy()
    rainView.onDestroy()
  }
}