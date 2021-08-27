package com.example.fab1

import android.app.AlertDialog
import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast


private var flag: Boolean = false

class CustomAlert constructor(private val context: Context) {
    init {
        flag = false
    }

    private val options: Array<String> = arrayOf(
        "Abducted",
        "Assaulted",
        "Met with an accident",
        "CX Escaped with Gold/Theft",
        "Others"
    )

    fun show() {


        var builder = AlertDialog.Builder(context)
        builder.setTitle("Select An Option")
            .setItems(options,
                { _, it ->
                    helper(options[it])

                })
        builder.setPositiveButton("False Alarm") { dialog, _ ->

            dialog.cancel()
            dialog.dismiss()
        }

        var alert = builder.create()
        alert.show()

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (!flag) {
                    helper("others")
                    alert.dismiss()

                }
            }
        }
        timer.start()
    }


    private fun helper(option: String) {
        flag = true
        apiCall(option)
    }

    private fun apiCall(option: String) {
        Toast.makeText(context, option, Toast.LENGTH_LONG).show()
    }


}




