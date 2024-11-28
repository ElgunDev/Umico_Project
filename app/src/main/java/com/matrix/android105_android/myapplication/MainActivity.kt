package com.matrix.android105_android.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matrix.android105_android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var batteryLowReceiver: BatteryLowReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BroadcastReceiver-in yaradılması və qeydiyyatı
        batteryLowReceiver = BatteryLowReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(batteryLowReceiver, intentFilter)

    }
    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(batteryLowReceiver)
    }
}