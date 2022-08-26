package com.scalio.githubusers.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View


fun View.show() {
    if (visibility == View.GONE || visibility == View.INVISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility == View.GONE || visibility == View.VISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.hide() {
    if (visibility == View.INVISIBLE || visibility == View.VISIBLE) {
        visibility = View.GONE
    }
}

val View.inflater
    get() = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater