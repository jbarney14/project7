package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

private lateinit var paint : Paint

private var balloons = Balloons()

private lateinit var b1Rect : RectF
private lateinit var b2Rect : RectF
private lateinit var b3Rect : RectF

private lateinit var balloon1 : Balloon
private lateinit var balloon2 : Balloon
private lateinit var balloon3 : Balloon

class GameView : View {

    var b1x = 0
    var b1y = 0
    var b1r = 0

    var b2x = 0
    var b2y = 0
    var b2r = 0

    var b3x = 0
    var b3y = 0
    var b3r = 0

constructor(context : Context, b1x : Int, b1y : Int, b1r : Int,
    b2x : Int, b2y : Int, b2r : Int, b3x : Int, b3y : Int, b3r : Int
) : super(context) {

    paint = Paint()
    paint.strokeWidth = 25f
    paint.isAntiAlias = true

    this.b1x = b1x
    this.b1y = b1y
    this.b1r = b1r
    b1Rect = RectF(b1x-b1r.toFloat(), b1y-b1r.toFloat(),
        b1x+b1r.toFloat(), b1y+b1r.toFloat())

    this.b2x = b2x
    this.b2y = b2y
    this.b2r = b2r
    b2Rect = RectF(b2x-b2r.toFloat(), b2y-b2r.toFloat(),
        b2x+b2r.toFloat(), b2y+b2r.toFloat())

    this.b3x = b3x
    this.b3y = b3y
    this.b3r = b3r
    b3Rect = RectF(b3x-b3r.toFloat(), b3y-b3r.toFloat(),
        b3x+b3r.toFloat(), b3y+b3r.toFloat())

}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 25f
        paint.color = Color.MAGENTA

        canvas.drawCircle( (b1Rect.left+b1r), b1Rect.top+b1r, b1r.toFloat(), paint)
        balloon1 = Balloon(b1x, b1y, b1r)
        balloons.setBalloons(balloon1, 1)
        canvas.drawCircle( (b2Rect.left+b2r), b2Rect.top+b2r, b2r.toFloat(), paint)
        balloon2 = Balloon(b2x, b2y, b2r)
        balloons.setBalloons(balloon2, 2)
        canvas.drawCircle( (b3Rect.left+b3r), b3Rect.top+b3r, b3r.toFloat(), paint)
        balloon3 = Balloon(b3x, b3y, b3r)
        balloons.setBalloons(balloon3, 3)
    }

}