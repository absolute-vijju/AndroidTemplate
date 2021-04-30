package com.example.androidtemplate.my_views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.text.DateFormat
import java.util.*


class MyCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
//            drawLine(0f, 0f, 100f, 200f, paint)
//            drawCircle(300f, 300f, 100f, paint)
//            drawText("Vijay Koshti", 300f, 300f, paint)
//            drawRect(100f, 100f, 300f, 200f, paint)
//            drawRoundRect(100f, 100f, 300f, 200f, 10f, 10f, paint)

            drawLine(200f, 0f, 400f, 200f, paint)    // \
            drawLine(400f, 200f, 400f, 400f, paint)  // |
            drawLine(400f, 400f, 200f, 600f, paint)  // /
            drawLine(200f, 600f, 0f, 400f, paint)    // \
            drawLine(0f, 400f, 0f, 200f, paint)      // |
            drawLine(0f, 200f, 200f, 0f, paint)      // /
        }
            DateFormat.getTimeInstance(DateFormat.SHORT).format(Date().time)
    }


    /**
     *For drawRect(10f, 10f, 100f, 200f, paint)
     *
     * Jyare left & top mlse tya draw krse then
     * Left & top mlse tyathi right side draw krvanu chalu krse then
     * Left & top mlse tyathi bottom side draw krvanu chalu krse
     *
     *
     *                                              *
     *                                              *
     *                                              *
     *                                              *                (right)
     *                    ***********************(left,top)***********************
     *                                              *
     *                                              *
     *                                              * (bottom)
     *                                              *
     *                                              *
     *                                              *
     *
     */


}