package com.github.travissuban.krefson

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultKrefsonTest {

    @Test
    fun putString() {
        val key = "KEY_STRING"
        val sample = "This is a simple, not so complicated String."
        Krefson.set(key, sample)

        val v = Krefson.get(key, "")
        Assert.assertEquals(sample, v)
    }

    @Test
    fun putBoolean() {
        val key = "KEY_BOOLEAN"
        val sample = true
        Krefson[key] = sample

        val v = Krefson[key, false]
        Assert.assertEquals(sample, v)
    }

    @Test
    fun putListOfStrings() {
        val key = "KEY_LIST_OF_STRINGS"
        val sample: List<String> = listOf("first", "second", "third")
        Krefson[key] = sample

        val v = Krefson[key, listOf<String>()]
        Assert.assertEquals(sample, v)
    }

    @Test
    fun putSomeData() {
        val key = "KEY_SOME_DATA"
        val sample = SampleData("someId", 300, listOf("first", "second", "third"))
        Krefson[key] = sample

        val v: SampleData? = Krefson[key]
        Assert.assertEquals(sample, v)
    }

    @Test
    fun contains() {
        val key = "KEY_CONTAINS"
        val sample = "something"
        Krefson[key] = sample

        Assert.assertTrue(key in Krefson)
    }

    @Test
    fun remove() {
        val key = "KEY_REMOVE"
        val sample = "something"
        Krefson[key] = sample
        Krefson.remove(key)

        Assert.assertFalse(Krefson.contains(key))
    }

    @Test
    fun putNotFound() {
        val key = "KEY_NOT_FOUND"
        Krefson.remove(key)

        val v: String? = Krefson[key]
        Assert.assertNull(v)
    }

    @Test
    fun defaultValue() {
        val key = "KEY_DEFAULT_VALUE"
        Krefson.remove(key)
        val default = "default"

        val v = Krefson[key, default]
        Assert.assertEquals(default, v)
    }
}