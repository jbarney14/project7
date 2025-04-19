package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView
    private lateinit var balloons : Balloons

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        parseJson()
        setView()

    }
    var b1x = 0
    var b1y = 0
    var b1r = 0

    var b2x = 0
    var b2y = 0
    var b2r = 0

    var b3x = 0
    var b3y = 0
    var b3r = 0

    fun parseJson() {

        val balloons3 = resources.openRawResource(R.raw.balloons3)

        val reader = BufferedReader(InputStreamReader(balloons3))
        val jsonString = reader.readText()
        reader.close()

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val balloon = jsonArray.getJSONObject(i)
            val x = balloon.getInt("x")
            val y = balloon.getInt("y")
            val r = balloon.getInt("radius")
            val inc = i + 1

            assignInstances(x, y, r, inc)

            Log.w("MainActivity", "Balloon $inc: x=$x, y=$y, radius=$r")
        }
    }

    fun setView() {
        gameView = GameView(this, b1x, b1y, b1r, b2x, b2y, b2r, b3x, b3y, b3r)
        setContentView(gameView)
    }

    fun assignInstances(x : Int, y : Int, r : Int, num : Int) {
        if(num == 1) {
           b1x = x
           b1y = y
           b1r = r
        } else {
            if(num == 2) {
                b2x = x
                b2y = y
                b2r = r
            } else {
                b3x = x
                b3y = y
                b3r = r
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                balloons.assessTouch(event.x.toInt(), event.y.toInt())
            }
        }
        return true
    }
}