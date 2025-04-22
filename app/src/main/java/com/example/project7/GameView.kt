package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

private lateinit var paint: Paint
private var clicks = 0

class GameView(context: Context, private val balloonList: MutableList<Balloon>,
               private val balloons: Balloons) : View(context) {

    private val balloonRects = mutableListOf<RectF>()
    private lateinit var toast: Toast

    init {
        paint = Paint().apply {
            strokeWidth = 25f
            isAntiAlias = true
            color = Color.MAGENTA
        }

        for ((index, balloon) in balloonList.withIndex()) {
            balloons.setBalloons(balloon, index + 1)
            val rect = RectF(
                balloon.x - balloon.r.toFloat(),
                balloon.y - balloon.r.toFloat(),
                balloon.x + balloon.r.toFloat(),
                balloon.y + balloon.r.toFloat()
            )
            balloonRects.add(rect)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((i, balloon) in balloonList.withIndex()) {
            if (!balloon.isHit) {
                val r = balloon.r.toFloat()
                canvas.drawCircle(
                    balloonRects[i].left + r, balloonRects[i].top + r, r, paint
                )
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            clicks++
            balloons.assessTouch(event.x.toInt(), event.y.toInt())

            when (balloons.checkGame(clicks)) {
                0 -> showToast("YOU WIN")
                2 -> showToast("YOU LOSE")
            }
        }
        return true
    }

    private fun showToast(message: String) {
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.show()
    }
}
