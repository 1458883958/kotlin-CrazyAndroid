package com.wdl.crazyandroiddemo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

/*
* 霓虹灯效果
* 1.定义handler配合timer使用，每隔2S变换每个textView的背景颜色
* 2.kotlin 中的数组简单使用以及when表达式；高阶函数，匿名内部类的使用
* */
class FrameLayoutActivity : AppCompatActivity() {

    private var currentColor = 0
    private val colors = arrayOf(R.color.color1, R.color.color2, R.color.color3
            , R.color.color4, R.color.color5, R.color.color6)
    private val names = arrayOf(R.id.view1, R.id.view2, R.id.view3,
            R.id.view4, R.id.view5, R.id.view6)
    private var textViews: MutableList<TextView> = ArrayList()
    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                0x123 -> {
                    textViews.forEachIndexed { index, textView ->
                        textView.setBackgroundResource(colors[(index + currentColor) % names.size])
                    }
                    currentColor++
                }
                else -> super.handleMessage(msg)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flayout)
        for (i in names.indices)
            textViews.add(i, findViewById(names[i]))
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.sendEmptyMessage(0x123)
            }

        }, 0, 200)
    }
}

