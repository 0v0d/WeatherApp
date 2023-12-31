package com.example.weatherapp.searchFragment

interface ISearchView {
  fun showToast()
  fun searchLocation(query: String)
}