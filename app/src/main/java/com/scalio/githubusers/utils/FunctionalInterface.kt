package com.scalio.githubusers.utils

import com.scalio.githubusers.features.search.data.PagingState


@FunctionalInterface
interface PagingStateListener {
    fun onState(state: PagingState,message:Any?=null)
}