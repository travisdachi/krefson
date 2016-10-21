# krefson

krefson is a really simple object store on Android. As the name suggest, it is written in Kotlin, it serialize object with Gson and store it as String in SharedPreference.

## Include in your project

Add this line to dependencies in a module's build.gradle
```
compile 'com.travissuban:krefson:0.1.0'
```

## Usage

```kotlin
//create a new instance of krefson
val krefson: Krefson = Krefson(context, "name", Gson())

//create a new instance of krefson using a default Gson
val krefson: Krefson = Krefson(context, "name")

//create a new instance of krefson using a default Gson and a default SharedPreference
val krefson: Krefson = Krefson(context)

//put a String
krefson["myKey"] = "sample string"

//get a String?
//you have to specify the type if you don't provide the defalut value
val s: String? = krefson["myKey"]

//get a String with default value
//providing a default value let the compiler knows the type plus, you get a non-nullable type
val s = krefson["myKey", "default string"]

//check an existance of "myKey"
val b: Boolean = "myKey" in krefson

//remove
krefson.remove("myKey")

//put an object
val v = MyObject()
krefson["myObjKey"] = v

//get an object
val v: MyObject? = krefson["myObjKey"]
val v = krefson["myObjKey", MyObject()]

```

## Contribute

I'm new to this so PR is always welcome.
