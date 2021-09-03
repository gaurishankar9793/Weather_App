package com.example.fab1

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.GestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var fabButton: FloatingActionButton
const val floatingButton = 1024
class CustomButton (private val context: Context?, private val message :String?,
                    private val layout: ViewGroup?,
                    private val waitTime :Long?,
                    private val positionX:Float?,
                    private val positionY: Float?
)
{
    data class Builder(
        var context: Context? = null, var message: String? = "N0 message",
        var layout: ViewGroup? = null,
        var waitTime: Long? = 3000,
        var positionX: Float? = 0F,
        var positionY: Float? = 0F
    ) {

        fun setContext(context: Context) = apply { this.context = context }
        fun setMessage(message: String) = apply { this.message = message }
        fun setWaitTime(waitTime: Long) = apply { this.waitTime = waitTime }
        fun setLayout(layout: ViewGroup) = apply { this.layout = layout }
        fun setX(positionX: Float) = apply { this.positionX = positionX }
        fun setY(positionY: Float) = apply { this.positionY = positionY }

        fun build() = CustomButton(context, message, layout, waitTime, positionX, positionY)

    }

    fun maker()
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
    fun removeButton()
    {
        layout?.removeView(fabButton)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun show() {
        fabButton = FloatingActionButton(context!!)
        fabButton.setImageResource(R.drawable.sosalert)

        fabButton.id = floatingButton
        fabButton.x = positionX!!
        fabButton.y = positionY!!
        fabButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 102, 102)) //(255,102,102)
        fabButton.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)


        val gestureHandler =
            GestureDetector(context, MyGestureDetector(fabButton, context, waitTime!!))
        val touchListener =
            View.OnTouchListener { View, event -> gestureHandler.onTouchEvent(event) }
        fabButton.setOnTouchListener(touchListener)
        val temp = layout?.findViewById<FloatingActionButton>(floatingButton)
        if (temp == null)
            layout?.addView(fabButton)


    }


}

