package com.example.weather

import android.os.SystemClock
import android.view.View
import com.example.core.domain.City

fun String?.filterEmpty(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun City?.toNameAndCountry(): String? {
    return this?.name.plus(", ").plus(this?.country)
}

fun View.onClick(cb: (View) -> Unit) {
    var lastClickTime = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return@setOnClickListener
        }

        lastClickTime = SystemClock.elapsedRealtime().toInt()
        cb(it)
    }
}