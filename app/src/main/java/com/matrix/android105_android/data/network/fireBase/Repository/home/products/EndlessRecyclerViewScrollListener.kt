package com.matrix.android105_android.data.network.fireBase.Repository.home.products

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(
    private val layoutManager: RecyclerView.LayoutManager
):RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    private var loading = true
    private var previousTotalItemCount = 0

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = when (layoutManager) {
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> 0
        }
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        if (!loading && (lastVisibleItemPosition + visibleThreshold) >= totalItemCount) {
            loading = true
            onLoadMore()
        }
    }
    abstract fun onLoadMore()
}