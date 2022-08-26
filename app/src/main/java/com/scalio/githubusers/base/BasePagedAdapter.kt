package com.scalio.githubusers.base

import android.annotation.SuppressLint
import android.view.View
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedAdapter<T : Any, VH : BasePagedViewHolder<T>> : PagingDataAdapter<T, VH>(BaseItemCallback()) {

    abstract var itemClickListener: (T) -> Unit
    private val itemsList by lazy { arrayListOf<T>() }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        item?.let {
            itemsList.add(it)
            holder.onBind(it, position)
        }
    }

    override fun getItemViewType(position: Int): Int = position


}

private class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

abstract class BasePagedViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(item: T, position: Int)
}
