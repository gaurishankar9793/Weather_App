package com.example.fab

import android.content.Context
import android.widget.Toast

class Toaster private constructor(val context:Context? ,val message :String?)
{
    data class Builder(var context:Context? =null ,var message :String? = "N0 message"){
        fun setContext(context: Context) =apply { this.context = context }

        fun setMessage(message: String) = apply {  this.message = message }

        fun build() = Toaster(context, message)
    }

   fun maker()
   {
       Toast.makeText(context,message,Toast.LENGTH_LONG).show()
   }

}