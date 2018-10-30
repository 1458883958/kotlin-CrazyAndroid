package com.wdl.crazyandroiddemo.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * author：   wdl
 * time： 2018/10/29 20:34
 * des：    自定义跟随手指移动的小球
 */
class Circle @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var currentX = 50f
    private var currentY = 60f
    private val p = Paint()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        p.color = Color.RED
        canvas!!.drawCircle(currentX, currentY, 15f, p)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //重新获取x y位置信息
        currentX = event.x
        currentY = event.y
        //重新绘制
        invalidate()
        //返回true表示已经处理该事件
        return true
    }

}