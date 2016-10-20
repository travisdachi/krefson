package com.travissuban.krefson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(string: String?): T? {
    return this.fromJson(string, object : TypeToken<T>() {}.type)
}