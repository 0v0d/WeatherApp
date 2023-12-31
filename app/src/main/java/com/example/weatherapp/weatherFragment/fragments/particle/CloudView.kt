package com.example.weatherapp.weatherFragment.fragments.particle

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.utility.Vector2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlin.random.Random

class CloudView(context: Context) : IAnimationView, View(context), CoroutineScope by MainScope() {
  private val paint = Paint()
  private val cloudImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cloud_image)
  private val rainCloudImage: Bitmap =
    BitmapFactory.decodeResource(resources, R.drawable.rain_cloud)
  private val clouds = mutableListOf<Vector2>()

  // 初期化
  init {
    paint.isAntiAlias = true
  }

  // 雲の数を設定
  override fun setValue(value: Int) {
    clouds.clear()
    val selectedBitmap = getCloudImage(value)

    for (i in 0 until value) {
      val x = if (value <= 40) {
        selectedBitmap.width.toFloat() * (Random.nextFloat() * 3)
      } else {
        selectedBitmap.width.toFloat() * (Random.nextFloat() * i)
      }
      val y = Random.nextFloat() / 2 * height / 2 - 200
      clouds.add(Vector2(x, y))
    }
    invalidate()
  }

  // 雲の描画
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    clouds.forEach { cloud ->
      val bitmap = getCloudImage(clouds.size)
      canvas.drawBitmap(bitmap, cloud.x, cloud.y, paint)
    }
    // 雲の位置を更新
    updateClouds()
  }

  // Viewが画面から消えたらジョブをキャンセル
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    cancel() // ジョブをキャンセルしてリソースを解放
  }

  // Viewが破棄されたら画像を解放
  override fun onDestroy() {
    cancel()
    cloudImage.recycle()
    rainCloudImage.recycle()
  }

  // 雲の位置を更新
  private fun updateClouds() {
    clouds.forEach { cloud ->
      // 雲の位置を右に移動
      cloud.x += 0.5f
      if (cloud.x > width) {
        // 画面の右端まで来たら左端に戻す
        cloud.x = -cloudImage.width.toFloat() * Random.nextFloat() * 3
        // 雲の位置をランダムに変更
        cloud.y = Random.nextFloat() / 2 * height / 2 - 200
      }
    }
    // 再描画
    invalidate()
  }

  // 雲の画像を取得
  private fun getCloudImage(size: Int): Bitmap {
    return if (size <= 40) cloudImage else rainCloudImage
  }
}