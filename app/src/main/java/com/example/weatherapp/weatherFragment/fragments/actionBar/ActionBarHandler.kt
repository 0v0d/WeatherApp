package com.example.weatherapp.weatherFragment.fragments.actionBar

import android.view.MenuItem
import com.example.weatherapp.utility.setupEnableActionBar
import com.example.weatherapp.weatherFragment.fragments.WeatherFragment

class ActionBarHandler(private val fragment: WeatherFragment) {
  //onCreateでActionBarの戻るボタンを有効にする
  fun setupActionBar() {
    fragment.setupEnableActionBar()
  }

  //ActionBarの戻るボタンの設定
  fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        fragment.showSearch()
        true
      }
      else -> false
    }
  }
}