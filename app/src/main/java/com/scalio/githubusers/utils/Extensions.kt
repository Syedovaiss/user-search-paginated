package com.scalio.githubusers.utils


fun String?.default(any: String? = null) = this ?: any ?: EMPTY_STRING
fun Int?.default(any: Int? = null) = this ?: any ?: 0

infix fun Int?.minus(value: Int) = if (this.default() == 0) 0 else this.default() + value

