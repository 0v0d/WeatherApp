package com.example.weatherapp.weatherFragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.FragmentNavigationManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.searchFragment.SearchFragment
import com.example.weatherapp.weatherFragment.ErrorHandler
import com.example.weatherapp.weatherFragment.IWeatherView
import com.example.weatherapp.weatherFragment.WeatherFragmentUIHandler
import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.fragments.actionBar.ActionBarHandler
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData
import com.example.weatherapp.weatherFragment.fragments.particle.WeatherAnimationHandler
import com.example.weatherapp.weatherFragment.presenter.WeatherPresenterHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherFragment : Fragment(), IWeatherView {
  private var _binding: FragmentWeatherBinding? = null
  private val binding get() = _binding!!

  private lateinit var uiHandler: WeatherFragmentUIHandler
  private lateinit var weatherPresenterHandler: WeatherPresenterHandler
  private val animationHandler = WeatherAnimationHandler()
  private val actionBarHandler = ActionBarHandler(this)
  private val errorHandler = ErrorHandler()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    actionBarHandler.setupActionBar()
  }

  //onOptionsItemSelectedで戻るボタンを押した時の処理を行う
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return actionBarHandler.onOptionsItemSelected(item)
  }

  //onCreateViewでFragmentのレイアウトを設定する
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentWeatherBinding.inflate(inflater, container, false)
    uiHandler = WeatherFragmentUIHandler(binding)
    uiHandler.createChildFragment(childFragmentManager)
    weatherPresenterHandler = WeatherPresenterHandler(this)
    return binding.root
  }

  //onViewCreatedでPresenterを設定する
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    uiHandler.setClickListener { weatherPresenterHandler.reload() }
    val location = arguments?.getString("location")
    weatherPresenterHandler.setupWeatherPresenter(location, lifecycleScope)
    animationHandler.onViewCreated(requireContext(), binding)
  }

  //onDestroyViewでViewを破棄する
  override fun onDestroyView() {
    super.onDestroyView()
    weatherPresenterHandler.onDestroy()
    _binding = null
    animationHandler.onDestroy()
  }

  //updateCurrentWeatherDataで現在の天気情報を更新する
  override fun updateMainContainer(weatherData: WeatherData) {
    try {
      bindWeatherData(weatherData)
    } catch (e: Exception) {
      showErrorMessage(R.string.ui_error)
    }
  }

  //updateWeeklyWeatherDataで週間天気予報を更新する
  override fun updateList(listViewData: ListViewData) {
    uiHandler.updateList(listViewData, ListItemClickListener())
    uiHandler.hideLoadingIndicator()
  }

  //showErrorMessageでエラーメッセージを表示する
  override fun showErrorMessage(messageResource: Int) {
    val errorMessage = resources.getText(messageResource).toString()
    errorHandler.showError(Bundle(), errorMessage)
  }

  //bindでデータをViewにバインドする
  private fun bindWeatherData(data: WeatherData) {
    CoroutineScope(Dispatchers.Main).launch {
      initializeAnimation(data.icon)
      uiHandler.updateUI(data)
    }
  }

  //アニメーションを初期化する
  private fun initializeAnimation(icon: String) {
    // CloudViewのクラウド数を設定
    val cloudValue = weatherPresenterHandler.getCloudValue(icon)
    val rainValue = weatherPresenterHandler.getRainValue(icon)
    animationHandler.initAnimation(cloudValue, rainValue)
  }

  //SearchFragmentに戻る
  fun showSearch() {
    // トランザクションの生成・コミット
    FragmentNavigationManager.replaceFragment(R.id.fragmentContainer, SearchFragment())
  }

  //ListViewのアイテムをクリックした時の処理を行う
  private inner class ListItemClickListener : AdapterView.OnItemClickListener {
    var pos = 0
    //onItemClickでListViewのアイテムをクリックした時の処理を行う
    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
      if (pos == position) {
        return
      }
      val data = weatherPresenterHandler.getItemClick(position)
      updateMainContainer(data)
      uiHandler.setCurrentWeatherButtonFlg(true)
      pos = position
    }
  }
}