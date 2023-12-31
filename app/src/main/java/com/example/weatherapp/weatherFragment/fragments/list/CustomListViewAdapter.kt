package com.example.weatherapp.weatherFragment.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.weatherapp.R
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewData
import com.example.weatherapp.weatherFragment.fragments.list.data.ListViewHolder

// ListViewの各要素を保持するためのクラス
class CustomListViewAdapter(
  private var itemList: ListViewData,
) : BaseAdapter() {
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val holder: ListViewHolder
    val view: View

    // convertViewがnullの場合は新しくViewを作る
    if (convertView == null) {
      // Viewを作成する
      view = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
      // ViewHolderを作成する
      holder = ListViewHolder(view)
      // ViewにViewHolderを設定する
      view.tag = holder
    } else {
      // convertViewがnullでない場合は再利用する
      view = convertView
      // ViewからViewHolderを取得する
      holder = convertView.tag as ListViewHolder
    }

    val currentItem = itemList

    // ListViewの各要素を設定する
    holder.dateView.text = currentItem.date[position]
    holder.weekView.text = currentItem.week[position]
    holder.timeView.text = currentItem.time[position]
    holder.tempView.text = currentItem.temp[position]
    holder.imageView.setImageResource(currentItem.imageIds[position])

    return view
  }

  // ListViewのデータを更新する
  fun updateData(newData: ListViewData) {
    itemList = newData
    // ListViewのデータが更新されたことを通知する
    notifyDataSetChanged()
  }

  // 以下のメソッドは今回は使わないので空の実装をしている
  override fun getCount(): Int {
    return itemList.date.size
  }

  override fun getItem(position: Int): Any {
    return itemList.date[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }
}