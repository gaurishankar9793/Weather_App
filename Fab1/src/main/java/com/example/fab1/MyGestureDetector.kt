package com.example.fab1

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyGestureDetector(
    private val fabButton: FloatingActionButton,
    private val context: Context,
    private val waitTime: Long
) : GestureDetector.SimpleOnGestureListener() {

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return super.onSingleTapUp(e)
    }


    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {

        if (e2 != null && e1 != null) {
            fabButton.x = checkX(fabButton.x + e2.x - e1.x)
            fabButton.y = checkY(fabButton.y + e2.y - e1.x)
        }


        return super.onScroll(e1, e2, distanceX, distanceY)
    }




    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
       // Log.d("Button", "single confirmed")
        CustomAlert(context, waitTime).show()
        return super.onSingleTapConfirmed(e)
    }

    private fun checkX(fl: Float): Float {
        val x = context.resources.displayMetrics.heightPixels.toFloat()
        return if (fl < 0 || fl >= x)
            0F
        else
            fl
    }

    private fun checkY(fl: Float): Float {
        val y = context.resources.displayMetrics.widthPixels.toFloat()
        return if (fl < 0 || fl >= y)
            0F
        else
            fl
    }


}
