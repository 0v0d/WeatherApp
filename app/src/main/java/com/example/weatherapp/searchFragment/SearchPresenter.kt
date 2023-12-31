package com.example.weatherapp.searchFragment

// SearchFragmentのPresenter
class SearchPresenter(private val view: ISearchView): ISearchPresenter {
  // 検索ボタンが押されたの入力文字列の処理
  override fun searchLocation(query: String) {
    val onlyAlphabet = query.matches("[a-zA-Z]+".toRegex())
    if (!onlyAlphabet) {
      view.showToast()
    } else {
      view.searchLocation(query)
    }
  }
}