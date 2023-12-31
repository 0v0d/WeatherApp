package com.example.weatherapp.searchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weatherapp.FragmentNavigationManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.utility.setupDisableActionBar
import com.example.weatherapp.weatherFragment.fragments.WeatherFragment

//SearchFragmentを作成する
class SearchFragment : Fragment(), ISearchView {
  private var _binding: FragmentSearchBinding? = null
  private val binding get() = _binding!!
  private lateinit var presenter: ISearchPresenter
  private lateinit var uiHandler: SearchFragmentUIHandler
  var location = ""

  //onCreateViewでFragmentのレイアウトを設定する
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSearchBinding.inflate(inflater, container, false)
    return binding.root
  }

  //onCreateでActionBarの戻るボタンを非表示に設定する
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupDisableActionBar()
  }

  //onViewCreatedでPresenterを設定する
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter = SearchPresenter(this)
    uiHandler = SearchFragmentUIHandler(binding)
    uiHandler.updateUI(presenter)
  }

  //SearchFragmentを破棄する
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  //WeatherInfoFragmentを表示する
  override fun searchLocation(query: String) {
    val weatherInfoFragment = WeatherFragment().apply {
      location = query
      arguments = Bundle()
      arguments?.putString("location", location)
    }
    FragmentNavigationManager.replaceFragment(R.id.fragmentContainer, weatherInfoFragment)
  }

  //Toastを表示する
  override fun showToast() {
    val message = resources.getText(R.string.text_error)
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
  }
}