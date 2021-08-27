package com.example.fab1

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.DialogInterface




class CustomButton private constructor(private val context: Context?, private val message :String?,
private val layout: ConstraintLayout?
)
{
    data class Builder(var context: Context? =null, var message :String? = "N0 message",
    var layout: ConstraintLayout? = null){
        fun setContext(context: Context) =apply { this.context = context }

        fun setMessage(message: String) = apply {  this.message = message }

        fun setLayout(layout: ConstraintLayout) = apply { this.layout = layout }
        fun build() = CustomButton(context, message,layout)
    }

    fun maker()
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
    fun show() {
        val fabButton = FloatingActionButton(context!!)
        fabButton.setImageResource(R.drawable.sosalert)
        fabButton.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 83, 73))
        val layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(50, 50, 50, 50)
        fabButton.layoutParams = layoutParams
        fabButton.setOnClickListener{
            Toast.makeText(context, "You clicked Floating Action Button", Toast.LENGTH_SHORT).show()
                CustomAlert(context).show()
        }
        layout?.addView(fabButton)



    }


}

