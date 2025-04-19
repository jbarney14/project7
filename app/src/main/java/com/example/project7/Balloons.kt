package com.example.project7

import android.graphics.Rect

class Balloons {

    private lateinit var balloon1: Balloon
    private lateinit var balloon2: Balloon
    private lateinit var balloon3: Balloon


    inner class Balloon (var isHit: Boolean) {
        fun popStatus() : Boolean {
            return this.isHit
        }
    }

    fun assessTouch(touchX : Int, touchY : Int) {

        val dx = touchX // - centerX
        val dy = touchY // - centerY
      //  val distance = sqrt(dx * dx + dy * dy)

      //  if (distance <= radius) {
            //inside circle vs rect
     //   }

    }

    fun setBalloons(balloon : Balloon, num : Int) {
            if(num == 1) {
                balloon1 = balloon
            } else {
                if(num == 2){
                    balloon2 = balloon
                } else {
                    balloon3 = balloon
                }
            }
    }

}