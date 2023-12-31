package com.example.weatherapp.searchFragment

import androidx.appcompat.widget.SearchView

class QueryTextListener(private val presenter: ISearchPresenter) : SearchView.OnQueryTextListener {
  // 検索ボタンが押された時の処理
  override fun onQueryTextSubmit(query: String?): Boolean {
    if (query != null) {
      presenter.searchLocation(query)
    }
    return false
  }
  // 未使用
  override fun onQueryTextChange(query: String?): Boolean {
    return false
  }
}