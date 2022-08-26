package com.scalio.githubusers.manager

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringResourceManager {
		fun getString(@StringRes resId: Int): String
		fun getString(@StringRes resId: Int, params: Any): String
		fun getString(@StringRes resId: Int, param1: Any, param2: Any): String
}

class DefaultStringResourceManager @Inject constructor(@ApplicationContext private val context: Context) :
		StringResourceManager {
		override fun getString(resId: Int) = context.getString(resId)

		override fun getString(resId: Int, params: Any) = context.getString(resId, params)

		override fun getString(resId: Int, param1: Any, param2: Any) =
						context.getString(resId, param1, param2)


}