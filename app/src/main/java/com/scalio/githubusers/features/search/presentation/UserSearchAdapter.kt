package com.scalio.githubusers.features.search.presentation

import android.view.ViewGroup
import com.scalio.githubusers.base.BasePagedAdapter
import com.scalio.githubusers.base.BasePagedViewHolder
import com.scalio.githubusers.databinding.ItemSearchUserBinding
import com.scalio.githubusers.features.search.data.Item
import com.scalio.githubusers.utils.inflater


class UserSearchAdapter(override var itemClickListener: (Item) -> Unit) : BasePagedAdapter<Item, UserSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchViewHolder {
        val binding = ItemSearchUserBinding.inflate(parent.inflater, parent, false)
        return UserSearchViewHolder(binding, itemClickListener)
    }
}

class UserSearchViewHolder(private val binding: ItemSearchUserBinding, private val itemClickListener: (Item) -> Unit) : BasePagedViewHolder<Item>(binding.root) {

    override fun onBind(item: Item, position: Int) {
        binding.users = item
        binding.root.setOnClickListener {
            itemClickListener.invoke(item)
        }

    }
}