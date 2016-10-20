package com.travissuban.krefson

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

open class Krefson @JvmOverloads constructor(context: Context, val name: String = "", val gson: Gson = Gson()) {

    val sharedPreference: SharedPreferences = if (name == "") {
        PreferenceManager.getDefaultSharedPreferences(context)
    } else {
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    inline operator fun <reified T> get(key: String): T? {
        return gson.fromJson(sharedPreference.getString(key, null))
    }

    inline operator fun <reified T> get(key: String, defaultValue: T): T {
        return gson.fromJson(sharedPreference.getString(key, null)) ?: defaultValue
    }

    inline operator fun <reified T> set(key: String, value: T) {
        sharedPreference.edit().putString(key, gson.toJson(value)).apply()
    }

    operator fun contains(key: String): Boolean {
        return sharedPreference.contains(key)
    }

    fun remove(key: String) {
        sharedPreference.edit().remove(key).apply()
    }
}