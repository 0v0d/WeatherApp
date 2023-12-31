package com.example.weatherapp.utility

import kotlin.random.Random

class Utility {
  fun getRandom(from: Int, until: Int): Int {
    return Random.nextInt(from, until)
  }
}