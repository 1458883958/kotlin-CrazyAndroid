package com.wdl.crazyandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout

class CalculatorActivity : AppCompatActivity() {

    private val btnValue = arrayOf(
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+")
    private lateinit var gridLayout: GridLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        gridLayout = findViewById(R.id.root)
        btnValue.mapIndexed { index, s ->
            //实例化按钮
            val button = Button(this)
            //设置text
            button.text = s
            //设置text字体大小
            button.textSize = 40f
            //设置按钮四周的空白区域
            button.setPadding(0, 35, 0, 35)
            //设置所在行，初始从第三行开始 +2代表 文本一行，清除按钮一行
            val rowSpec = GridLayout.spec(index / 4 + 2)
            //设置所在列 一行有4个列
            val columnSpec = GridLayout.spec(index % 4)
            //
            val params = GridLayout.LayoutParams(rowSpec, columnSpec)
            //充满父容器
            params.setGravity(Gravity.CENTER)
            gridLayout.addView(button, params)
        }

    }
}
