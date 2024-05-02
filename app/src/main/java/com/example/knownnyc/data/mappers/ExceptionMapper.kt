package com.example.knownnyc.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.knownnyc.commons.AppError
import com.example.knownnyc.commons.TAG
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun Throwable.toError() : AppError {
    Log.d(TAG, "mapping exception: $this")
    val error = when (this) {
        is retrofit2.HttpException -> "Unknown response"
        is IOException -> "Network error"
        else -> "unkwown error"
    }

    return AppError(error, throwable = this)

}