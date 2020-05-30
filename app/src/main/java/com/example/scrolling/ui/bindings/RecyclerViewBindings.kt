package com.example.scrolling.ui.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBindings {

    companion object {

        @JvmStatic
        @BindingAdapter("setAdapter")
        fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("onScrollListener")
        fun bindScrollListener(recyclerView: RecyclerView, onScrollListener: () -> Unit) {


            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount: Int = recyclerView.layoutManager?.childCount ?: 0
                    val pastVisibleItem: Int =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val total: Int = recyclerView.adapter?.itemCount ?: 0

                    if (visibleItemCount + pastVisibleItem >= total) {
                        onScrollListener.invoke()
                    }

                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }
}