package com.sample.broadcastreceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var exampleBroadcastReceiver = ExampleBroadcastReceiver()

    /**
     * With following lifecycle methods the broadcast receiver is alive while the activity is displayed
     * If we need the broadcast receiver alive even when the Activity is on the foreground: we should play with onCreate and onDestroy
     * If we want to keep it as much as our App is running then it should be registered on App.onCreate method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filter = IntentFilter("com.sample.broadcastreceiver.EXAMPLE_ACTION")
        registerReceiver(exampleBroadcastReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(exampleBroadcastReceiver)
    }
}
