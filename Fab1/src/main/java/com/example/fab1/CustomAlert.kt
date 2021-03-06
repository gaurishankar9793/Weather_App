package com.example.fab1

import android.app.AlertDialog
import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import com.example.fab1.api.Details


private var flag: Boolean = false

class CustomAlert(private val context: Context, private val waitTime: Long) {
    init {
        flag = false
    }

    private val options: Array<String> = arrayOf(
        "Netflix",
        "",
        "Met with an accident",
        "Call A Friend",
        "Others"
    )

    fun show() {


        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select An Option")
            .setItems(options,
                { _, it ->
                    helper(options[it])

                })
        builder.setPositiveButton("False Alarm") { dialog, _ ->
            flag = true
            dialog.cancel()
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()

        val timer = object : CountDownTimer(waitTime, 1000) {
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
        val viewModel = ViewModel()
        val details = Details("hello",option)
        viewModel.current(details)
        Toast.makeText(context, "Your SOS  $option has been send Successfully", Toast.LENGTH_LONG).show()

    }


}




