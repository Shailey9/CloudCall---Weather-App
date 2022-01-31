package com.example.cloudcall

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        val ll = findViewById<LinearLayout>(R.id.linearLayout1)
        val line = findViewById<TextView>(R.id.line)

        var intent = Intent(this, MainActivity::class.java )

        ll.animate().translationY(450f).setDuration(1500).startDelay = 0
        line.animate().translationY(400f).setDuration(1500).startDelay = 0

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(intent)
            }
        }, 6000)
    }
}