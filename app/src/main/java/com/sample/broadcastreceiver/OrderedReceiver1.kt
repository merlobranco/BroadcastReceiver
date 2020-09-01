package com.sample.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class OrderedReceiver1 : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Retrieving the 3 pieces of data provided by the previous broadcast receiver
        var rc = resultCode
        var rd = resultData
        var re = getResultExtras(true) // If it is null will be initialized
        var se = re.getString("stringExtra")

        rc++
        se += "->OR1"

        var toastText = "OR1\nresultCode: $rc\nresultData: $rd\nstringExtra: $se"

        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()

        rd = "OR1"
        re.putString("stringExtra", se)

        setResult(rc, rd, re)
    }
}