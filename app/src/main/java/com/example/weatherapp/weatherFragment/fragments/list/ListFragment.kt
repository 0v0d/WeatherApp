package com.example.weatherapp.weatherFragment.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData

class ListFragment : Fragment() {
  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!
  private var customListViewAdapter: CustomListViewAdapter? = null
  //ListViewを作成する
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentListBinding.inflate(inflater, container, false)
    return binding.root
  }

  //ListViewを更新する
  fun updateWeatherList(
    listViewData: ListViewData,
    itemClickListener: AdapterView.OnItemClickListener
  ) {
    if (customListViewAdapter == null) {
      customListViewAdapter = CustomListViewAdapter(listViewData)
      binding.weatherList.adapter = customListViewAdapter
    } else {
      customListViewAdapter!!.updateData(listViewData)
    }
    binding.weatherList.onItemClickListener = itemClickListener
  }

  //ListViewを破棄する
  override fun onDestroyView() {
    super.onDestroyView()
    customListViewAdapter = null
    _binding?.apply {
      weatherList.adapter = null
      weatherList.onItemClickListener = null
    }
    _binding = null
  }
}