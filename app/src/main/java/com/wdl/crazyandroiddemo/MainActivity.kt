package com.wdl.crazyandroiddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建一个线性布局管理器
        val linearLayout = LinearLayout(this)
        setContentView(linearLayout)
        linearLayout.orientation = LinearLayout.VERTICAL
        val show = TextView(this)
        show.text = "hello kotlin"
        val bn = Button(this)
        bn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        bn.text = "点击"
        linearLayout.addView(show)
        linearLayout.addView(bn)
        bn.setOnClickListener {
            show.text = "onClick() + ${Date()}"
        }

    }
}
