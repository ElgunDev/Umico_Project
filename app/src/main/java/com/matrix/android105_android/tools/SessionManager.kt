package com.matrix.android105_android.tools

import android.content.Context
import android.content.SharedPreferences

import androidx.core.content.edit

object SessionManager {

    const val KEY_LANGUAGE = "Language"
    private lateinit var sharedPref : SharedPreferences


    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }




    var language
        get() = LanguageEnum.find(sharedPref.getString(KEY_LANGUAGE, LanguageEnum.AZ.name))
        set(value) {
            sharedPref.edit { putString(KEY_LANGUAGE, value.name) }
        }
}