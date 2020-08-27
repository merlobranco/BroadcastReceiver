package com.sample.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class ExampleBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("BroadcastReceiver","I received something")
        Toast.makeText(context, "I received something", Toast.LENGTH_SHORT).show()
        if (Intent.ACTION_BOOT_COMPLETED == intent!!.action) {
            Log.i("BroadcastReceiver","Boot completed")
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show()
        }

        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
            Log.i("BroadcastReceiver","Connectivy changed")
            Toast.makeText(context, "Connectivy changed", Toast.LENGTH_SHORT).show()
        }
    }
}