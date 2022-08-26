package com.scalio.githubusers.app

import com.scalio.githubusers.R
import com.scalio.githubusers.base.BaseActivity
import com.scalio.githubusers.databinding.ActivityScalioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScalioActivity : BaseActivity<ActivityScalioBinding>() {

		override fun getLayout(): Int = R.layout.activity_scalio
}