package com.example.weatherapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentNavigationManager {
  private lateinit var fragmentManager: FragmentManager
  //初期化
  fun initialize(manager: FragmentManager) {
    fragmentManager = manager
  }

  //Fragmentの置き換え
  fun replaceFragment(containerId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().apply {
      replace(containerId, fragment)
      addToBackStack(null)
      commit()
    }
  }
}