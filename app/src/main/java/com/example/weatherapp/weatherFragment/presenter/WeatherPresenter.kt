package com.example.weatherapp.weatherFragment.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.weatherapp.R
import com.example.weatherapp.weatherFragment.IWeatherModel
import com.example.weatherapp.weatherFragment.IWeatherPresenter
import com.example.weatherapp.weatherFragment.IWeatherView
import com.example.weatherapp.weatherFragment.data.WeatherData
import com.example.weatherapp.weatherFragment.logic.WeatherDataSource
import com.example.weatherapp.weatherFragment.logic.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherPresenter(private val view: IWeatherView) : IWeatherPresenter {
  private val model: IWeatherModel = WeatherModel()
  private val dataSource = WeatherDataSource()
  //都市名を設定する
  override fun setLocation(location: String) {
    //CurrentWeatherPresenterに都市名を設定する
    dataSource.setLocation(location)
  }

  //天気情報を取得する
  override fun getWeatherData(lifecycle: LifecycleCoroutineScope) {
    //コルーチンを使って非同期処理を行うことで、UIスレッドをブロックしないようにする
    //lifeCycleCoroutineScope.launchを使うことで、ライフサイクルに合わせて処理を行うようにする
    //Dispatchers.Mainを指定することで、メインスレッドで処理を行うようにする
    lifecycle.launch(Dispatchers.Main) {
      try {
        //WeatherDataSourceからデータを取得する
        view.updateMainContainer(dataSource.loadCurrentWeatherData()!!)
        val weeklyData = dataSource.loadWeeklyWeatherDataList()
        view.updateList(model.getListViewData(weeklyData!!))
      } catch (e: Exception) {
        view.showErrorMessage(R.string.location_error)
      }
    }
  }

  //現在の天気情報を再取得する
  override fun reload() {
    //CurrentWeatherPresenterに再取得するように指示する
    try {
      view.updateMainContainer(dataSource.getCurrentWeatherData())
    } catch (e: Exception) {
      view.showErrorMessage(R.string.reload_error)
    }
  }

  //ListViewのアイテムをクリックしたときに表示するデータを得る
  override fun getItemClick(position: Int): WeatherData {
    return model.getItemClick(position, dataSource.getWeeklyWeatherDataList())
  }

  //Presenterが破棄されたときに呼び出される
  override fun onDestroy() {
    dataSource.onDestroy()
  }

  //雨の値を取得する
  override fun getRainValue(description: String): Int {
    return model.getRainValue(description)
  }

  //雲の値を取得する
  override fun getCloudValue(description: String): Int {
    return model.getCloudValue(description)
  }
}