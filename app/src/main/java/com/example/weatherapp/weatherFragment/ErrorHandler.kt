package com.example.weatherapp.weatherFragment

import android.os.Bundle
import com.example.weatherapp.FragmentNavigationManager
import com.example.weatherapp.R
import com.example.weatherapp.errorFragment.ErrorFragment

class ErrorHandler {
  //エラー画面を表示する
  fun showError(bundle: Bundle, message: String) {
    val errorFragment = ErrorFragment().apply {
      //argumentsはFragment間でデータを受け渡すためのもの
      arguments = bundle
      arguments?.putString("errorMessage", message)
    }
    FragmentNavigationManager.replaceFragment(R.id.fragmentContainer, errorFragment)
  }
}