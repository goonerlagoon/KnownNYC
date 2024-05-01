package com.example.knownnyc.commons

/**
* extension function to provide TAG value
*/

val Any.TAG: String
    get() {
        return if (!javaClass.isAnonymousClass) {
            val name: String = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23) // first 23 chars
        } else {
            val name: String = javaClass.name
            if (name.length <= 23) name.substring(name.length - 23, name.length) else name // last 23
        }
    }

