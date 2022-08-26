package com.scalio.githubusers.features.search.presentation

import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.flatMap
import androidx.recyclerview.widget.RecyclerView
import com.scalio.githubusers.features.search.data.Item
import com.scalio.githubusers.features.search.data.PagingState
import com.scalio.githubusers.utils.PagingStateListener
import com.scalio.githubusers.utils.invisible
import com.scalio.githubusers.utils.show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@BindingAdapter(value = ["bind_users", "scope", "state_listener"])
fun RecyclerView.bindUsers(
        users: PagingData<Item>?,
        scope: CoroutineScope,
        listener: PagingStateListener
) {
    users?.let {
        show()
        scope.launch {
            (adapter as UserSearchAdapter?)?.submitData(it)
        }

        (adapter as UserSearchAdapter?)?.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                listener.onState(PagingState.LOADING)
            } else if (loadState.refresh is LoadState.NotLoading || loadState.prepend is LoadState.NotLoading || loadState.refresh is LoadState.NotLoading) {
                listener.onState(PagingState.SUCCESS)
            } else {
                when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }?.let {
                    listener.onState(PagingState.ERROR,it.error.message)
                }
            }
        }
    } ?: invisible()
}
