package com.example.weatherapp.utility

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
fun Fragment.setupDisableActionBar() {
  // Fragmentが関連付けられているActivityのActionBarを取得
  val actionBar = (activity as? AppCompatActivity)?.supportActionBar
  // ActionBarがnullでない場合にsetDisplayHomeAsUpEnabled(false)を呼び出す
  actionBar?.setDisplayHomeAsUpEnabled(false)
}

fun Fragment.setupEnableActionBar() {
  // Fragmentが関連付けられているActivityのActionBarを取得
  val actionBar = (activity as? AppCompatActivity)?.supportActionBar
  // ActionBarがnullでない場合にsetDisplayHomeAsUpEnabled(true)を呼び出す
  actionBar?.setDisplayHomeAsUpEnabled(true)
  //onOptionsItemSelectedを有効にする
  setHasOptionsMenu(true)
}