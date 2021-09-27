package com.masai.lifecycleawaretimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timerClass = TimerClass(this,lifecycle)
        timerClass.startTimer()
    }
}