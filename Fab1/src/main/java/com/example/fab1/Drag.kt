package com.example.fab1

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton


var xDown = 0.00F
var yDown = 0.00F

var xStart = 0.00F
var yStart = 0.00F

class Drag(
    private val fabButton: FloatingActionButton,
    private val context: Context,
    private val waitTime: Long
) {


    @SuppressLint("ClickableViewAccessibility")
    fun temp() {
        fabButton.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View?, e: MotionEvent?): Boolean {

                when (e?.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        xDown = e.x
                        yDown = e.y
                        xStart = e.x
                        yStart = e.y


                    }
                    MotionEvent.ACTION_MOVE -> {
                        val movedX = e.x
                        val movedY = e.y
                        val distanceX = movedX - xDown
                        val distanceY = movedY - yDown

                        fabButton.x = checkX(fabButton.x + distanceX)
                        fabButton.y = checkY(fabButton.y + distanceY)

                    }/*
                    here we will call our alert only when our action_down and action_Up are at same (x,y)
                    this is a click
                    besides this everything is considered as a movement
                    */
                    MotionEvent.ACTION_UP -> {
                        //   TODO("Doubt here how to handle multiple clicks")

                        val movedX = e.x
                        val movedY = e.y
                        val distanceX = movedX - xStart
                        val distanceY = movedY - yStart
                        if (distanceX == 0F && distanceY == 0F) {
                            CustomAlert(context, waitTime).show()
                            return false
                        }
                    }
                }
                return true
            }

        })
    }

    /**
     * to restrict  our button from going out of the UI
     */
    fun checkX(fl: Float): Float {
        val x = context.resources.displayMetrics.heightPixels.toFloat()
        return if (fl < 0 || fl >= x)
            0F
        else
            fl
    }

    fun checkY(fl: Float): Float {
        val y = context.resources.displayMetrics.widthPixels.toFloat()
        return if (fl < 0 || fl >= y)
            0F
        else
            fl
    }
}