package com.example.weatherapp.weatherFragment.fragments.list.data

data class ListViewData(
  val date: MutableList<String>,
  val week: MutableList<String>,
  val time: MutableList<String>,
  val imageIds: MutableList<Int>,
  val temp: MutableList<String>,
)