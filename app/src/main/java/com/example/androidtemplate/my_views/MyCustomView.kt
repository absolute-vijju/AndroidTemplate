package com.example.androidtemplate.my_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MyCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 12f
        isAntiAlias = true
        strokeCap = Paint.Cap.SQUARE
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawColor(Color.YELLOW)    // Background color of Canvas
            simpleLine(this)
            quadrilateral(this)
            howPaddingWorks(this)
            drawStar(this)
        }
    }

    private fun drawStar(canvas: Canvas?) {
        canvas?.apply {
            var x = width.toFloat()
            var y = height.toFloat()

            val paddingRight = 16f + paddingRight
            val paddingLeft = 16f + paddingLeft
            val paddingTop = 16f + paddingTop
            val paddingBottom = 16f + paddingBottom

            val centerX = x / 2
            val centerY = y / 2

            x -= paddingRight
            y -= paddingBottom

            path.apply {
                moveTo(centerX, paddingTop)
                lineTo(x, y)
                lineTo(paddingLeft, centerY)
                lineTo(x, centerY)
                lineTo(paddingLeft,y)
                lineTo(centerX, paddingTop)

                drawPath(this,paint)
            }

        }
    }

    private fun simpleLine(canvas: Canvas?) {
        canvas?.apply {
            var x = width.toFloat()
            var y = height.toFloat()

            val paddingRight = 16f + paddingRight
            val paddingLeft = 16f + paddingLeft
            val paddingTop = 16f + paddingTop
            val paddingBottom = 16f + paddingBottom

            val centerX = x / 2
            val centerY = y / 2

            x -= paddingRight
            y -= paddingBottom

            path.moveTo(centerX, paddingTop)
            path.lineTo(centerX, y)

            drawPath(path, paint)

        }
    }

    private fun createPath(sides: Int = 6, radius: Float = 100f): Path {
        val cx = (width / 2).toFloat()
        val cy = (height / 2).toFloat()

        val path = Path()
        val angle = 2.0 * Math.PI / sides
        path.moveTo(
            cx + (radius * Math.cos(0.0)).toFloat(),
            cy + (radius * Math.sin(0.0)).toFloat()
        )
        for (i in 1 until sides) {
            path.lineTo(
                cx + (radius * Math.cos(angle * i)).toFloat(),
                cy + (radius * Math.sin(angle * i)).toFloat()
            )
        }
        path.close()
        return path
    }

    private fun quadrilateral(canvas: Canvas?) {
        var x = width.toFloat()
        var y = height.toFloat()

        val paddingRight = 16f + paddingRight
        val paddingLeft = 16f + paddingLeft
        val paddingTop = 16f + paddingTop
        val paddingBottom = 16f + paddingBottom

        val centerX = x / 2
        val centerY = y / 2

        x -= paddingRight
        y -= paddingBottom

        canvas?.apply {
            path.apply {
                moveTo((centerX), (paddingTop))
                lineTo(x, centerY)
                lineTo(centerX, y)
                lineTo(paddingLeft, centerY)
                lineTo((centerX), (paddingTop))
            }
            drawPath(path, paint)
        }
    }

    private fun howPaddingWorks(canvas: Canvas?) {
        canvas?.apply {
            val left = paddingLeft
            val top = paddingTop
            val right = width - paddingRight
            val bottom = height - paddingBottom
            drawLine(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
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