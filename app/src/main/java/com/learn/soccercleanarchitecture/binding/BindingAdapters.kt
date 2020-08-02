package com.learn.soccercleanarchitecture.binding

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.learn.soccercleanarchitecture.R

@BindingAdapter(value = ["imageUrl", "imageRequestListener"], requireAll = false)
fun ImageView.bindImage(url: String?, listener: RequestListener<Drawable?>?) {
    Glide.with(context)
        .load(url)
        .listener(listener)
        .placeholder(context?.getDrawable(R.drawable.ic_no_image))
        .into(this)
}

@BindingAdapter(value = ["list", "childLayout"], requireAll = false)
fun ChipGroup.setChipList(list: List<String>?, childLayoutId: Int?) {
    list?.forEach { item ->
        val chip: Chip? = if (childLayoutId == null) {
            Chip(context).apply { text = item }
        } else {
            val inflater = context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as? LayoutInflater
            inflater?.inflate(childLayoutId, this, false)
        } as? Chip
        chip?.let {
            it.text = item
            addView(it)
        }
    }
}

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.customRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener != null) setOnRefreshListener(listener)
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.customRefreshing(refreshing: Boolean?) {
    isRefreshing = refreshing == true
}

@BindingAdapter("onScrollListener")
fun RecyclerView.customScrollListener(listener: RecyclerView.OnScrollListener?) {
    if (listener != null) addOnScrollListener(listener)
}

@BindingAdapter("safeClick")
fun View.safeClick(listener: View.OnClickListener?) {
    val blockInMillis: Long = 500
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) {
            return@setOnClickListener
        }
        lastClickTime = SystemClock.elapsedRealtime()
        listener?.onClick(this)
    }
}