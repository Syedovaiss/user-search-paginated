package com.scalio.githubusers.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter(value = ["recyclerAdapter"])
fun RecyclerView.bindAdapter(recyclerAdapter: RecyclerView.Adapter<*>) {
    this.adapter = recyclerAdapter
}

@BindingAdapter(value = ["decorators_enabled"])
fun RecyclerView.bindDecorator(decorators: Boolean?) {
    decorators?.let { isEnabled ->
        if (isEnabled) {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }
}

@BindingAdapter(value = ["apply_image"])
fun ImageView.applyImage(url: String?) {
    url?.let {
        if (it.isNotBlank()) {
            show()
            Glide.with(context)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(this)
        } else invisible()

    } ?: invisible()
}


@BindingAdapter(value = ["toggle_visibility"])
fun View.toggleVisibility(isVisible: Boolean) {
    if (isVisible) show() else hide()
}
