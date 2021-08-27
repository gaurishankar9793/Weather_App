package com.example.fab1

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

class CustomAlert constructor(private val context: Context) {

  private  val options: Array<String> = arrayOf(
        "Abducted",
        "Assaulted",
        "Met with an accident",
        "CX Escaped with Gold/Theft",
        "Others"
    )

   fun show() {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Select An Option")
    builder.setItems(options,
    {
        _, it ->
        apiCall(options[it])

    })
    builder.show()
   }

   private fun apiCall(option :String)
   {
       Toast.makeText(context, option, Toast.LENGTH_LONG).show()
   }
}
