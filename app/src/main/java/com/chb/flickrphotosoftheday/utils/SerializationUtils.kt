package com.chb.flickrphotosoftheday.utils

import android.os.Bundle
import java.io.Serializable

inline fun <reified T: Serializable>Bundle.getSerializableCompat(key: String): T? {
    return if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}
