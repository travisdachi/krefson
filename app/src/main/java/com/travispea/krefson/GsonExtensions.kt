package com.travispea.krefson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Travis on 8/18/2016 AD.
 */
inline fun <reified T> Gson.fromJson(string: String?): T? {
    return this.fromJson(string, object : TypeToken<T>() {}.type)
}