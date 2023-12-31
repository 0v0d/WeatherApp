package com.example.weatherapp.weatherFragment

import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.utility.DateTimeUtility
import com.example.weatherapp.utility.WeatherImageManager
import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.fragments.DetailContainerFragment
import com.example.weatherapp.weatherFragment.fragments.list.ListFragment
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData

class WeatherFragmentUIHandler(private val binding: FragmentWeatherBinding) {
  private val detailContainerFragment = DetailContainerFragment()
  private val listFragment = ListFragment()
  private val dateUtility = DateTimeUtility()
  private val imageManager = WeatherImageManager()
  //子フラグメントを作成する
  fun createChildFragment(childFragmentManager: FragmentManager) {
    childFragmentManager.beginTransaction().apply {
      replace(binding.detailContainer.id, detailContainerFragment)
      replace(binding.weatherList.id, listFragment)
      commit()
    }
  }

  //ListViewを更新する
  fun updateList(
    listViewData: ListViewData,
    itemClickListener: AdapterView.OnItemClickListener) {
    listFragment.updateWeatherList(listViewData, itemClickListener)
  }

  //UIを更新する
  fun updateUI(data: WeatherData) {
    binding.apply {
      infoContainer.isVisible = true
      location.text = data.location
      temp.text = data.temp
      detailContainerFragment.bind(data)
      updateBackGround(data)
      weather.text = data.description
    }
  }

  //ローディングインジケータを非表示にする
  fun hideLoadingIndicator() {
    binding.apply {
      loader.isVisible = false
      mainContainer.isVisible = true
    }
  }

  //currentWeatherButtonの表示を切り替える
  fun setCurrentWeatherButtonFlg(isVisible: Boolean) {
    binding.apply {
      currentWeatherButton.isVisible = isVisible
    }
  }

  //現在の天気情報を表示するボタンを押した時の処理を行う
  fun setClickListener(reload: () -> Unit) {
    binding.currentWeatherButton.setOnClickListener {
      reload()
      setCurrentWeatherButtonFlg(false)
    }
  }

  //背景画像を更新する
  private fun updateBackGround(data: WeatherData) {
    val isNight = dateUtility.isNight(data.forecastTime, data.sunrise, data.sunset)
    val image = imageManager.getBackGroundImageId(data.main, isNight)
    binding.infoContainer.setBackgroundResource(image)
  }
}