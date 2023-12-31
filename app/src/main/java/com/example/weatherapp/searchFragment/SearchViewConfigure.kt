package com.example.weatherapp.searchFragment

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.weatherapp.R

typealias SearchView = androidx.appcompat.widget.SearchView

class SearchViewConfigure(
  private val searchView: SearchView,
  private val presenter: ISearchPresenter
) {
  //SearchViewの設定
  fun configure() {
    searchView.apply {
      setOnQueryTextListener(QueryTextListener(presenter))
      isIconified = false
      setQuery("", false)
      setOnCloseListener { true }
      setIconifiedByDefault(false)
    }
    changeTextColor()
    changeSearchIconColor()
  }

  //TextColorを変更
  private fun changeTextColor() {
    val color = ContextCompat.getColor(searchView.context, R.color.white)
    val editText =
      searchView.findViewById<androidx.appcompat.widget.SearchView.SearchAutoComplete>(
        androidx.appcompat.R.id.search_src_text
      )

    editText.apply {
      setHintTextColor(color)
      setTextColor(color)
    }
  }

  //SearchIconの色を変更
  private fun changeSearchIconColor() {
    searchView.apply {
      val searchIcon = findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
      searchIcon?.setImageResource(R.drawable.search)
      val closeIcon = findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
      val color = ContextCompat.getColor(searchView.context, R.color.white)
      closeIcon?.setColorFilter(color)
    }
  }
}

