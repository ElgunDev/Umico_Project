package com.matrix.android105_android.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryLowReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_LOW) {

            Toast.makeText(context, "Batareya səviyyəsi aşağıdır!", Toast.LENGTH_SHORT).show()
        }
    }
}