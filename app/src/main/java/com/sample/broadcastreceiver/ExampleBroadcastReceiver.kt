package com.sample.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class ExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("com.sample.broadcastreceiver.EXAMPLE_ACTION" == intent!!.action) {
            val receivedText = intent.getStringExtra("com.sample.broadcastreceiver.EXTRA_TEXT")
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show()
        }
    }
}