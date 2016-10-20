package com.travissuban.krefson

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class KrefsonTest {

    var krefson: Krefson = Krefson(InstrumentationRegistry.getContext(), "test")

    @Test
    fun putString() {
        val key = "KEY_STRING"
        val sample = "This is a simple, not so complicated String."
        krefson.set(key, sample)

        val v = krefson.get(key, "")
        assertEquals(sample, v)
    }

    @Test
    fun putBoolean() {
        val key = "KEY_BOOLEAN"
        val sample = true
        krefson[key] = sample

        val v = krefson[key, false]
        assertEquals(sample, v)
    }

    @Test
    fun putListOfStrings() {
        val key = "KEY_LIST_OF_STRINGS"
        val sample: List<String> = listOf("first", "second", "third")
        krefson[key] = sample

        val v = krefson[key, listOf<String>()]
        assertEquals(sample, v)
    }

    @Test
    fun putSomeData() {
        val key = "KEY_SOME_DATA"
        val sample = SampleData("someId", 300, listOf("first", "second", "third"))
        krefson[key] = sample

        val v: SampleData? = krefson[key]
        assertEquals(sample, v)
    }

    @Test
    fun contains() {
        val key = "KEY_CONTAINS"
        val sample = "something"
        krefson[key] = sample

        assertTrue(key in krefson)
    }

    @Test
    fun remove() {
        val key = "KEY_REMOVE"
        val sample = "something"
        krefson[key] = sample
        krefson.remove(key)

        assertFalse(krefson.contains(key))
    }

    @Test
    fun putNotFound() {
        val key = "KEY_NOT_FOUND"
        krefson.remove(key)

        val v: String? = krefson[key]
        assertNull(v)
    }

    @Test
    fun defaultValue() {
        val key = "KEY_DEFAULT_VALUE"
        krefson.remove(key)
        val default = "default"

        val v = krefson[key, default]
        assertEquals(default, v)
    }
}