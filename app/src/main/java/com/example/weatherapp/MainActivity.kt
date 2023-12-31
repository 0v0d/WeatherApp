package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.weatherapp.searchFragment.SearchFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    // ステータスバーの色を変更
    window.statusBarColor = ContextCompat.getColor(this, R.color.black)

    // FragmentManagerの取得
    FragmentNavigationManager.initialize(supportFragmentManager)
    // 初期画面を設定
    FragmentNavigationManager.replaceFragment(R.id.fragmentContainer, SearchFragment())
  }
}