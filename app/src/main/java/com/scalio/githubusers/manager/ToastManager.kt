package com.scalio.githubusers.manager

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ToastManager {
		fun showToast(@StringRes message: Int)
		fun showToast(message: String)
}

class DefaultToastManager @Inject constructor(
		@ApplicationContext private val context: Context,
		private val stringResourceManager: StringResourceManager
) :
		ToastManager {
		override fun showToast(message: Int) {
				Toast.makeText(context, stringResourceManager.getString(message), Toast.LENGTH_SHORT).show()
		}

		override fun showToast(message: String) {
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
		}


}