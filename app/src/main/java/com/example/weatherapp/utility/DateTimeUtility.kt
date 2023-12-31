package com.example.weatherapp.utility

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// 日時に関するユーティリティクラス
class DateTimeUtility {
  // 現在の日時を取得する
  fun getNowDate(): String {
    val timestamp = (System.currentTimeMillis() + 9 * 60 * 60 * 1000) /1000
    return timestampToDate(timestamp) + " " + timestampToTime(timestamp)
  }
  // 年月日を月日に変換する
  fun formatDate(inputDate: String): String {
    // 入力フォーマットと出力フォーマットを設定
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MM/dd", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(inputDate) as Date)
  }

  // 日付文字列から曜日を計算する
  fun calculateDayOfWeek(dateString: String): String {
    // 日付フォーマットを設定
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = sdf.parse(dateString) as Date
    // 曜日を取得
    return SimpleDateFormat("EEE", Locale.getDefault()).format(date).toString()
  }

  // タイムスタンプを年月日時刻に変換する
  fun timestampToYearDate(timestamp: Long): String {
    return timestampToDateTime(timestamp, "yyyy-MM-dd HH:mm:ss")
  }

  // タイムスタンプを月日に変換する
  fun timestampToDate(timestamp: Long): String {
    return timestampToDateTime(timestamp, "MM/dd")
  }

  // タイムスタンプを時刻に変換する
  fun timestampToTime(timestamp: Long): String {
    return timestampToDateTime(timestamp, "HH:mm")
  }

  // タイムスタンプを指定されたフォーマットの日時文字列に変換する（内部メソッド）
  private fun timestampToDateTime(timestamp: Long, outputPattern: String): String {
    val sdf = SimpleDateFormat(outputPattern, Locale.getDefault())
    val date = Date(timestamp * 1000)
    return sdf.format(date)
  }

  // 指定された日時が夜かどうかを判定する
  fun isNight(dateTimeString: String, sunriseTime: String, sunsetTime: String): Boolean {
    // 日時フォーマットを設定し、日時を取得
    val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())
    val date = dateFormat.parse(dateTimeString)
    val calendar = Calendar.getInstance()

    // 日付を設定された日付に変更
    if (date != null) {
      calendar.time = date
    }

    // 日の出と日の入りの時間が設定されていない場合は、デフォルトの時間を設定
    var sunriseTemp = sunriseTime
    var sunsetTemp = sunsetTime
    if (sunriseTemp == "-" || sunsetTemp == "-") {
      sunriseTemp = "05:30"
      sunsetTemp = "17:30"
    }

    // 日の出と日の入りの時間をパース
    val sunrise = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(sunriseTemp)
    val sunset = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(sunsetTemp)
    val current = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(
      SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
    )

    // 現在の時間が日の出より後かつ日の入りより前かを判定
    if (current != null) {
      return current.after(sunset) || current.before(sunrise)
    }
    return false
  }
}