@file:Suppress("UNUSED_EXPRESSION")

package com.wdl.crazyandroiddemo

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity(), View.OnClickListener {
    //动态改变图片透明度 以及动态更换图片
    override fun onClick(v: View) {
        when (v) {
            plus -> {
                alpha += 20
            }
            minus -> {
                alpha -= 20
            }
            next -> image.setImageResource(images[++current % images.size])
        }
        if (alpha >= 255) alpha = 255
        if (alpha <= 0) alpha = 0
        image.alpha = alpha.toFloat()
    }

    private val images = arrayOf(R.drawable.e, R.drawable.i, R.drawable.p, R.drawable.u)
    private var current = 2
    private var alpha = 255
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        plus.setOnClickListener(this)
        minus.setOnClickListener(this)
        next.setOnClickListener(this)
        //根据触摸事件动态显示触摸点的位图
        image.setOnTouchListener { _, event ->
            //获取位图
            val bitmapDrawable = image.drawable as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap
            //计算缩放比例
            val scale = 1.0 * bitmap.height / image.height
            //计算实际的位点
            var x: Int = (event.x * scale).toInt()
            var y: Int = (event.y * scale).toInt()
            if (x + 120 > bitmap.width) x = bitmap.width - 120
            if (y + 120 > bitmap.height) y = bitmap.height - 120
            //设置位图
            image1.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 120, 120))
            return@setOnTouchListener false
        }

        //快捷关联联系人列表中的18806039939
        contact.assignContactFromPhone("18806039939",false)
    }
}
