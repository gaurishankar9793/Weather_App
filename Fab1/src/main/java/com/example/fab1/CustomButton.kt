package com.example.fab1

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CustomButton (private val context: Context?, private val message :String?,
private val layout: ConstraintLayout?,
private val waitTime :Long?
)
{
    data class Builder(var context: Context? =null, var message :String? = "N0 message",
    var layout: ConstraintLayout? = null,
    var waitTime:Long? = 3000){
        fun setContext(context: Context) =apply { this.context = context }

        fun setMessage(message: String) = apply {  this.message = message }
        fun setWaitTime(waitTime: Long) = apply{ this.waitTime = waitTime}
        fun setLayout(layout: ConstraintLayout) = apply { this.layout = layout }
        fun build() = CustomButton(context, message,layout,waitTime)
    }

    fun maker()
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
    fun show() {
        val fabButton = FloatingActionButton(context!!)
        fabButton.setImageResource(R.drawable.sosalert)
        fabButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 83, 73))
        val layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(50, 50, 50, 50)
        fabButton.layoutParams = layoutParams
        /**
         * This drag class will handle our onTouch listener
         * onClick is also added inside this
         */
        Drag(fabButton, context, waitTime!!).temp()

        layout?.addView(fabButton)


        // TODO("destroy the button ? ")

    }


}

