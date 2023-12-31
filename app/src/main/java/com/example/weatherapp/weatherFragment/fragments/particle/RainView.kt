package com.example.weatherapp.weatherFragment.fragments.particle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.example.weatherapp.utility.Utility
import com.example.weatherapp.utility.Vector2
import com.example.weatherapp.weatherFragment.fragments.particle.data.RainParticleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlin.random.Random

class RainView(context: Context) : IAnimationView, View(context), CoroutineScope by MainScope() {
  private val paint = Paint()
  private var particles: MutableList<RainParticleData>? = null
  private var particlesValue = 0
  private val utility = Utility()

  // 初期化
  init {
    paint.color = Color.WHITE
    // 雨滴の太さ
    paint.strokeWidth = 5f
  }

  // 雨の粒子の数を設定
  override fun setValue(value: Int) {
    // 古いパーティクルがあればクリア
    particles?.clear()
    // 新しいパーティクルリストを作成
    particles = mutableListOf()
    particlesValue = value
    for (i in 0 until particlesValue) {
      particles?.add(createRandomRainParticle())
    }
  }

  // 雨滴の描画
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    particles?.forEach { particle ->
      paint.alpha = particle.alpha
      canvas.drawLine(
        particle.pos.x, particle.pos.y, particle.pos.x,
        particle.pos.y + particle.size, paint
      )
    }
    updateParticles()
  }

  //Viewが画面から消えたらジョブをキャンセル
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    //ジョブをキャンセルしてリソースを解放
    cancel()
  }

  //Viewが再度アタッチされた時に呼び出される
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    //新しいパーティクルリストを作成する
    particles = mutableListOf()
    setValue(particlesValue)
  }

  //Viewが破棄されたらonDestroyを呼び出す
  override fun onDestroy() {
    //不要になったらクリア
    particles?.clear()
    //参照を解放する
    particles = null
  }

  // 雨の粒子を更新
  private fun updateParticles() {
    // lifecycleScopeを使ってコルーチンを開始する
    for (particle in particles!!) {
      particle.pos.y += particle.speed
      // 画面外に出たら上に戻す
      if (particle.pos.y >= height) {
        particle.pos.y = Random.nextInt(0, height / 6).toFloat()
      }
    }
    // 再描画
    invalidate()
  }

  // 雨の粒子をランダムに生成
  private fun createRandomRainParticle(): RainParticleData {
    val x = utility.getRandom(0, width).toFloat()// X座標をランダムに設定
    val y = utility.getRandom(0, height).toFloat()// Y座標をランダムに設定
    val size = utility.getRandom(20, 30).toFloat()// 雨滴の長さをランダムに設定
    val speed = utility.getRandom(20, 40).toFloat()// 落下速度をランダムに設定
    val alpha = utility.getRandom(100, 255)// 透明度をランダムに設定
    return RainParticleData(Vector2(x, y), size, speed, alpha)
  }
}