package com.sample.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OrderedReceiver2(val coroutineScope: CoroutineScope) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        /**
         * Telling to the system we need more time, even when the onReceive returned
         * Later we will call to the system through pendingResult.finish() that our long job is done
         * https://developer.android.com/reference/android/content/BroadcastReceiver#goAsync()
         */
        val pendingResult = goAsync()

        coroutineScope.launch {
            SystemClock.sleep(10000)

            // Retrieving the 3 pieces of data provided by the previous broadcast receiver
            var rc = pendingResult.resultCode
            var rd = pendingResult.resultData
            var re = pendingResult.getResultExtras(true) // If it is null will be initialized
            var se = re.getString("stringExtra")

            rc++
            se += "->OR2"

            var toastText = "OR2\nresultCode: $rc\nresultData: $rd\nstringExtra: $se"
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()

            rd = "OR2"
            re.putString("stringExtra", se)

            pendingResult.setResult(rc, rd, re)
            pendingResult.finish()
        }
    }
}