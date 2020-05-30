package com.example.scrolling.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scrolling.R
import com.example.scrolling.databinding.RowItemBinding
import com.example.scrolling.model.User
import javax.inject.Inject

class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowItemBinding>(
            layoutInflater,
            R.layout.row_item,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = userList[position]
    }

    fun setUserList(_userList: List<User>?) {
        userList = _userList ?: emptyList()
    }

    inner class UserViewHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}