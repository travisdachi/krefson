package com.travispea.krefson

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Travis on 7/31/2016 AD.
 */
open class Krefson(context: Context, val name: String, val gson: Gson = Gson()) {

    val sharedPreference: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline operator fun <reified T> get(key: String): T? {
        return gson.fromJson(sharedPreference.getString(key, null), object : TypeToken<T>() {}.type)
    }

    inline operator fun <reified T> get(key: String, defaultValue: T): T {
        return gson.fromJson(sharedPreference.getString(key, null), object : TypeToken<T>() {}.type) ?: defaultValue
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