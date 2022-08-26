package com.scalio.githubusers.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : ViewBinding> : AppCompatActivity() {

		private lateinit var _binding: VM
		protected val binding get() = _binding

		override fun onCreate(savedInstanceState: Bundle?) {
				super.onCreate(savedInstanceState)
				_binding = DataBindingUtil.setContentView(this, getLayout())
				init()
		}

		abstract fun getLayout(): Int

		protected open fun init() = Unit

}