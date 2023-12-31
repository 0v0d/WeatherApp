package com.example.weatherapp.searchFragment

import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.utility.DateTimeUtility
import com.example.weatherapp.utility.WeatherImageManager

class SearchFragmentUIHandler(private val binding: FragmentSearchBinding) {
  private val dateUtility = DateTimeUtility()
  private val imageManager = WeatherImageManager()
  // UIの設定
  fun updateUI(presenter: ISearchPresenter){
    SearchViewConfigure(binding.search, presenter).configure()
    val isNight = dateUtility.isNight(dateUtility.getNowDate(), "-", "-")
    val image = imageManager.getBackGroundImageId("Clear", isNight)
    binding.searchLayout.setBackgroundResource(image)
  }
}