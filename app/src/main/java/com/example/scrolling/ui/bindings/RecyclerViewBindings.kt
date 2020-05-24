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
    }
}