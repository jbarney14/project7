package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

private lateinit var paint : Paint

private lateinit var b1Rect : RectF
private lateinit var b2Rect : RectF
private lateinit var b3Rect : RectF

private lateinit var toast: Toast

private var clicks = 0

class GameView : View {

    private lateinit var balloons : Balloons

    var b1x = 0
    var b1y = 0
    var b1r = 0
    lateinit var balloon1 : Balloon

    var b2x = 0
    var b2y = 0
    var b2r = 0
    lateinit var balloon2 : Balloon

    var b3x = 0
    var b3y = 0
    var b3r = 0
    lateinit var balloon3 : Balloon

constructor(context : Context, balloon1: Balloon, balloon2 : Balloon, balloon3 : Balloon,
            balloons : Balloons) : super(context) {

    paint = Paint()
    paint.strokeWidth = 25f
    paint.isAntiAlias = true

    this.balloons = balloons

    this.b1x = balloon1.x
    this.b1y = balloon1.y
    this.b1r = balloon1.r
    this.balloon1 = balloon1
    balloons.setBalloons(balloon1, 1)
    b1Rect = RectF(b1x-b1r.toFloat(), b1y-b1r.toFloat(),
        b1x+b1r.toFloat(), b1y+b1r.toFloat())

    this.b2x = balloon2.x
    this.b2y = balloon2.y
    this.b2r = balloon2.r
    this.balloon2 = balloon2
    balloons.setBalloons(balloon2, 2)
    b2Rect = RectF(b2x-b2r.toFloat(), b2y-b2r.toFloat(),
        b2x+b2r.toFloat(), b2y+b2r.toFloat())

    this.b3x = balloon3.x
    this.b3y = balloon3.y
    this.b3r = balloon3.r
    this.balloon3 = balloon3
    balloons.setBalloons(balloon3, 3)
    b3Rect = RectF(b3x-b3r.toFloat(), b3y-b3r.toFloat(),
        b3x+b3r.toFloat(), b3y+b3r.toFloat())

}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 25f
        paint.color = Color.MAGENTA

        if(!balloon1.isHit) {
            canvas.drawCircle((b1Rect.left + b1r), b1Rect.top + b1r, b1r.toFloat(), paint)
        }

        if(!balloon2.isHit) {
            canvas.drawCircle((b2Rect.left + b2r), b2Rect.top + b2r, b2r.toFloat(), paint)
        }

        if(!balloon3.isHit) {
            canvas.drawCircle((b3Rect.left + b3r), b3Rect.top + b3r, b3r.toFloat(), paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                clicks++
                balloons.assessTouch(event.x.toInt(), event.y.toInt())
                Log.w("MainActivity", clicks.toString() + balloons.checkGame(clicks).toString())
                if(balloons.checkGame(clicks) == 0) {
                    toast = Toast.makeText(context, "YOU WIN", Toast.LENGTH_LONG)
                    toast.show()
                } else {
                    if(balloons.checkGame(clicks) == 2) {
                        toast = Toast.makeText(context, "YOU LOSE", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            }
        }
        return true
    }
}
