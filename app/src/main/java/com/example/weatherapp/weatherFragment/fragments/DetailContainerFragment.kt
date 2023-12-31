package com.example.weatherapp.weatherFragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentDetailContainerBinding
import com.example.weatherapp.weatherFragment.data.WeatherData

class DetailContainerFragment : Fragment() {
  private var _binding: FragmentDetailContainerBinding? = null
  private val binding get() = _binding!!

  //DetailContainerを作成する
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentDetailContainerBinding.inflate(inflater, container, false)
    return binding.root
  }

  //UIを更新する
  fun bind(data: WeatherData) {
    binding.apply {
      binding.detailContainer.isVisible = true
      minTemp.text = data.tempMin
      maxTemp.text = data.tempMax
      sunrise.text = data.sunrise
      sunset.text = data.sunset
      wind.text = data.windSpeed
      pressure.text = data.pressure
      humidity.text = data.humidity
      forecastTime.text = data.forecastTime
    }
  }

  //DetailContainerを破棄する
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}