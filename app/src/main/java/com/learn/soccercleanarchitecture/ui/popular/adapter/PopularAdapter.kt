package com.learn.soccercleanarchitecture.ui.popular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseRecyclerAdapter
import com.learn.soccercleanarchitecture.binding.safeClick
import com.learn.soccercleanarchitecture.databinding.ItemMovieBinding
import com.learn.soccercleanarchitecture.databinding.ItemPageBinding
import com.learn.soccercleanarchitecture.model.ModelItem
import com.learn.soccercleanarchitecture.model.MovieItem
import com.learn.soccercleanarchitecture.model.PageHeaderItem

class PopularAdapter(
    private val onClickMovie: (MovieItem) -> Unit?,
    private val onClickMovieFavorite: (MovieItem) -> Unit?
) :
    BaseRecyclerAdapter<ModelItem>(callBack = object : DiffUtil.ItemCallback<ModelItem>() {
        override fun areItemsTheSame(oldItem: ModelItem, newItem: ModelItem): Boolean {
            if (oldItem is MovieItem && newItem is MovieItem) {
                return oldItem.id == newItem.id
            } else if (oldItem is PageHeaderItem && newItem is PageHeaderItem) {
                return oldItem.page == newItem.page
            }
            return false

        }

        override fun areContentsTheSame(
            oldItem: ModelItem,
            newItem: ModelItem
        ): Boolean {
            return oldItem.equals(newItem)
        }
    }) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        if (viewType != null && viewType != -1) {
            return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        } else {
            throw Throwable("Not found this view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieItem -> R.layout.item_movie
            is PageHeaderItem -> R.layout.item_page
            else -> -1
        }
    }

    override fun bind(binding: ViewDataBinding, item: ModelItem, position: Int) {
        if (binding is ItemMovieBinding) {
            binding.apply {
                this.item = item as MovieItem
                imageViewFavorite.safeClick(View.OnClickListener {
                    onClickMovieFavorite.invoke(item)
                })
                root.safeClick(View.OnClickListener {
                    onClickMovie.invoke(item)
                })
            }
        } else if (binding is ItemPageBinding) {
            binding.page = (item as PageHeaderItem).page.toString()
        }
    }
}