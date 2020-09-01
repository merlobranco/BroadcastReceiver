package com.sample.broadcastreceiver

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class MainActivity : AppCompatActivity() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    val activityJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass activityJob, we can cancel all coroutines launched by coroutineScope by calling
     * activityJob.cancel()
     */
    val coroutineScope = CoroutineScope(activityJob + Dispatchers.Main)

    private lateinit var orderedReceiver1: OrderedReceiver1
    private lateinit var orderedReceiver2: OrderedReceiver2

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
        filter.priority = 1
        registerReceiver(orderedReceiver1, filter)

        orderedReceiver2 = OrderedReceiver2(coroutineScope)
        val filter2 = IntentFilter("com.sample.EXAMPLE_ACTION")
        filter2.priority = 2
        registerReceiver(orderedReceiver2, filter2)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(orderedReceiver1)
        unregisterReceiver(orderedReceiver2)
        coroutineScope.cancel()
    }
}
