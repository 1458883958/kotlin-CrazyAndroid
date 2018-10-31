package com.wdl.crazyandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class ClockActivity : AppCompatActivity() {

    private lateinit var start: Button
    private lateinit var chronometer: Chronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
        start = findViewById(R.id.start)
        chronometer = findViewById(R.id.chronometer)
        chronometer.setOnChronometerTickListener {
            //判断计时时间是否超过20s
            if (SystemClock.elapsedRealtime() - it.base > 20 * 1000) {
                chronometer.stop()
                start.isEnabled = true
            }
        }
        //点击事件
        start.setOnClickListener {
            //设置计时器的起始时间
            chronometer.base = SystemClock.elapsedRealtime()
            //开始计时
            chronometer.start()
            start.isEnabled = false
        }
    }
}
