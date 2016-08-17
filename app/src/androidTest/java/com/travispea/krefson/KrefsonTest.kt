package com.travispea.krefson

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Travis on 7/31/2016 AD.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class KrefsonTest {

    var mKrefson: Krefson = Krefson(InstrumentationRegistry.getContext(), "test")

    @Test
    fun putString() {
        val key = "KEY_STRING"
        val sample = "This is a simple, not so complicated String."
        mKrefson.set(key, sample)

        val v = mKrefson.get(key, "")
        assertEquals(sample, v)
    }

    @Test
    fun putBoolean() {
        val key = "KEY_BOOLEAN"
        val sample = true
        mKrefson[key] = sample

        val v = mKrefson[key, false]
        assertEquals(sample, v)
    }

    @Test
    fun putListOfStrings() {
        val key = "KEY_LIST_OF_STRINGS"
        val sample: List<String> = listOf("first", "second", "third")
        mKrefson[key] = sample

        val v = mKrefson[key, listOf<String>()]
        assertEquals(sample, v)
    }

    @Test
    fun putSomeData() {
        val key = "KEY_SOME_DATA"
        val sample = SomeData("someId", 300, listOf("first", "second", "third"))
        mKrefson[key] = sample

        val v: SomeData? = mKrefson[key]
        assertEquals(sample, v)
    }

    @Test
    fun contains() {
        val key = "KEY_CONTAINS"
        val sample = "something"
        mKrefson[key] = sample

        assertTrue(key in mKrefson)
    }

    @Test
    fun remove() {
        val key = "KEY_REMOVE"
        val sample = "something"
        mKrefson[key] = sample
        mKrefson.remove(key)

        assertFalse(mKrefson.contains(key))
    }

    @Test
    fun putNotFound() {
        val key = "KEY_NOT_FOUND"
        mKrefson.remove(key)

        val v: String? = mKrefson[key]
        assertNull(v)
    }

    @Test
    fun defaultValue() {
        val key = "KEY_DEFAULT_VALUE"
        mKrefson.remove(key)
        val default = "default"

        val v = mKrefson[key, default]
        assertEquals(default, v)
    }
}