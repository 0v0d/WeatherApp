package com.example.weatherapp.errorFragment

import com.example.weatherapp.databinding.FragmentErrorBinding

class ErrorFragmentUIHandler(private val binding: FragmentErrorBinding) {
  //ボタンが押されたときにSearchFragmentを表示する
  fun setUpRetryButton(showSearch: () -> Unit) {
    binding.retryButton.setOnClickListener {
      showSearch()
    }
  }

  //エラーメッセージを表示する
  fun setUpErrorMessage(errorMessage: String?) {
    binding.errorText.apply {
      text = errorMessage ?: ""
    }
  }
}