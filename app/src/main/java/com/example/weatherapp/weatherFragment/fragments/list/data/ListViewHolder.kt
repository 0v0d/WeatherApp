package com.example.weatherapp.weatherFragment.fragments.list.data

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val dateView: TextView = itemView.findViewById(R.id.weatherDate)
  val weekView: TextView = itemView.findViewById(R.id.weatherWeek)
  val timeView: TextView = itemView.findViewById(R.id.weatherTime)
  val tempView: TextView = itemView.findViewById(R.id.weatherTemp)
  val imageView: ImageView = itemView.findViewById(R.id.weatherIcon)
}