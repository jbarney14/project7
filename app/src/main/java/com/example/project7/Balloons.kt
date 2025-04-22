package com.example.project7

import android.util.Log
import kotlin.math.sqrt

class Balloons {

    private lateinit var gameView: GameView
    private val balloonList = mutableListOf<Balloon>()
    private var balloonsHit = 0

    fun setGameView(view: GameView) {
        this.gameView = view
    }

    fun setBalloons(balloon: Balloon, num: Int) {
        if (balloonList.size >= num) {
            balloonList[num - 1] = balloon
        } else {
            // Fill in gaps if needed
            while (balloonList.size < num - 1) {
                balloonList.add(Balloon(0, 0, 0)) // Dummy placeholder
            }
            balloonList.add(balloon)
        }
    }

    fun assessTouch(touchX: Int, touchY: Int) {
        for (balloon in balloonList) {
            val dx = touchX - balloon.x
            val dy = touchY - balloon.y
            val dist = sqrt((dx * dx + dy * dy).toDouble())

            if (dist <= balloon.r && !balloon.isHit) {
                balloon.isHit = true
                balloonsHit++
                gameView.postInvalidate()
                break // Only pop one balloon per touch
            }
        }
    }

    fun checkGame(clicks: Int): Int {
        val maxAttempts = MainActivity.BALLOON_NUM + 3

        return when {
            clicks < maxAttempts && balloonsHit == MainActivity.BALLOON_NUM -> 0 // win
            clicks < maxAttempts -> 1 // continue
            else -> if (balloonsHit == MainActivity.BALLOON_NUM) 0 else 2 // win or lose
        }
    }
}
