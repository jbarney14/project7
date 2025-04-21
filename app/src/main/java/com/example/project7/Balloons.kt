package com.example.project7

import android.graphics.Rect
import android.util.Log
import kotlin.math.absoluteValue
import kotlin.math.sqrt

class Balloons() {

    private lateinit var balloon1: Balloon
    private lateinit var balloon2: Balloon
    private lateinit var balloon3: Balloon

    private var balloonsHit = 0

    private lateinit var gameView: GameView

    fun setGameView(view: GameView) {
        this.gameView = view
    }

    fun assessTouch(touchX: Int, touchY: Int) {
        val dx1 = touchX - balloon1.x
        val dy1 = touchY - balloon1.y
        val dist1 = sqrt((dx1 * dx1 + dy1 * dy1).toDouble())

        val dx2 = touchX - balloon2.x
        val dy2 = touchY - balloon2.y
        val dist2 = sqrt((dx2 * dx2 + dy2 * dy2).toDouble())

        val dx3 = touchX - balloon3.x
        val dy3 = touchY - balloon3.y
        val dist3 = sqrt((dx3 * dx3 + dy3 * dy3).toDouble())

        if (dist1 <= balloon1.r) {
            balloon1.isHit = true
            balloonsHit++
            gameView.postInvalidate()
            Log.w("MainActivity", "pop 1")
        }

        if (dist2 <= balloon2.r) {
            balloon2.isHit = true
            balloonsHit++
            gameView.postInvalidate()
            Log.w("MainActivity", "pop 2")
        }

        if (dist3 <= balloon3.r) {
            balloon3.isHit = true
            balloonsHit++
            gameView.postInvalidate()
            Log.w("MainActivity", "pop 3")
        }
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

    fun checkGame(clicks : Int) : Int {

        val maxAttempts = MainActivity.BALLOON_NUM + 3

        if (clicks < 6) {

            if (balloonsHit == MainActivity.BALLOON_NUM) {
                return 0 //win
            } else {
                return 1 //cont
            }

        }
        return 2 //lose
    }
}