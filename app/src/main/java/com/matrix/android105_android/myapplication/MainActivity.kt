package com.matrix.android105_android.myapplication

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matrix.android105_android.R
import com.matrix.android105_android.tools.LanguageEnum
import com.matrix.android105_android.tools.LanguageEnum.Companion.lowercase
import com.matrix.android105_android.tools.LocaleWrapper
import com.matrix.android105_android.tools.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var batteryLowReceiver: BatteryLowReceiver

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale(SessionManager.language.lowercase())
        val context = LocaleWrapper.updateLocale(newBase, locale)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        batteryLowReceiver = BatteryLowReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(batteryLowReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryLowReceiver)
    }

    fun recreateActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    fun changeLanguage(languageCode: LanguageEnum) {
        SessionManager.language = languageCode
        recreateActivity()
    }
}