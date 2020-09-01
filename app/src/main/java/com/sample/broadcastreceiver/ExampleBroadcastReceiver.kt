package com.sample.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent!!.getStringExtra("com.sample.EXTRA_STRING")
        Toast.makeText(context, "EBR triggered: $message", Toast.LENGTH_SHORT).show()
    }
}