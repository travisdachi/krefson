package com.github.travissuban.krefson

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class Krefson(context: Context, val name: String, val gson: Gson = Gson()) {

    val pref: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline operator fun <reified T> get(key: String): T? {
        return gson.fromJson(pref.getString(key, null))
    }

    inline operator fun <reified T> get(key: String, defaultValue: T): T {
        return gson.fromJson(pref.getString(key, null)) ?: defaultValue
    }

    inline operator fun <reified T> set(key: String, value: T) {
        pref.edit().putString(key, gson.toJson(value)).apply()
    }

    operator fun contains(key: String): Boolean {
        return pref.contains(key)
    }

    fun remove(key: String) {
        pref.edit().remove(key).apply()
    }

    companion object {
        lateinit var default: SharedPreferences
        var gson: Gson = Gson()

        inline operator fun <reified T> get(key: String): T? {
            return gson.fromJson(default.getString(key, null))
        }

        inline operator fun <reified T> get(key: String, defaultValue: T): T {
            return gson.fromJson(default.getString(key, null)) ?: defaultValue
        }

        inline operator fun <reified T> set(key: String, value: T) {
            default.edit().putString(key, gson.toJson(value)).apply()
        }

        operator fun contains(key: String): Boolean {
            return default.contains(key)
        }

        fun remove(key: String) {
            default.edit().remove(key).apply()
        }
    }
}