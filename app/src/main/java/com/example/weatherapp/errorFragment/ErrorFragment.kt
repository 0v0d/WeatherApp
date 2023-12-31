package com.example.weatherapp.errorFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.FragmentNavigationManager
import com.example.weatherapp.R
import com.example.weatherapp.searchFragment.SearchFragment
import com.example.weatherapp.databinding.FragmentErrorBinding
import com.example.weatherapp.utility.setupDisableActionBar

class ErrorFragment : Fragment(){
  private var _binding: FragmentErrorBinding? = null
  private val binding get() = _binding!!
  private lateinit var uiHandler: ErrorFragmentUIHandler

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentErrorBinding.inflate(inflater, container, false)
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
    uiHandler = ErrorFragmentUIHandler(binding)
    uiHandler.setUpRetryButton { showSearch() }
    setUpErrorMessage()
  }

  //ErrorFragmentを破棄する
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  //エラーメッセージを表示する
  private fun setUpErrorMessage() {
    val errorMessage = arguments?.getString("errorMessage")
    uiHandler.setUpErrorMessage(errorMessage)
  }

  //SearchFragmentを表示する
  private fun showSearch() {
    FragmentNavigationManager.replaceFragment(R.id.fragmentContainer, SearchFragment())
  }
}