package com.sample.broadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var orderedReceiver1: OrderedReceiver1

    /**
     * With following lifecycle methods the broadcast receiver is alive while the activity is displayed
     * If we need the broadcast receiver alive even when the Activity is on the foreground: we should play with onCreate and onDestroy
     * If we want to keep it as much as our App is running then it should be registered on App.onCreate method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orderedReceiver1 = OrderedReceiver1()
        val filter = IntentFilter("com.sample.EXAMPLE_ACTION")
        registerReceiver(orderedReceiver1, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(orderedReceiver1)
    }
}
