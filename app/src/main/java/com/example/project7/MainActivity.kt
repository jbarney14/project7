package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
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
    private lateinit var currBalloon : Balloon

    private val balloonArray = mutableListOf<Balloon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        parseJson()
        setView()

    }

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

            currBalloon = Balloon(x, y, r)

            balloonArray.add(i, currBalloon)

            MainActivity.BALLOON_NUM = jsonArray.length()

            Log.w("MainActivity", "Balloon $inc: x=$x, y=$y, radius=$r")
        }
    }

    fun setView() {
        balloons = Balloons()
        gameView = GameView(this, balloonArray, balloons)
        balloons.setGameView(gameView)
        setContentView(gameView)
    }

    companion object {
        var BALLOON_NUM = 0
    }

}