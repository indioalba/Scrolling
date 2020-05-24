package com.example.scrolling.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scrolling.databinding.RowItemBinding
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.view.viewmodel.ScrollingViewModel

class UserAdapter(private val layoutId: Int, private val viewModel:  ScrollingViewModel) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: List<User>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowItemBinding>(layoutInflater, viewType, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int = layoutId

    override fun getItemCount(): Int = userList?.size?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    fun setUserList(_userList: List<User>) {
        userList =_userList
    }

    inner class UserViewHolder(val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ScrollingViewModel, position: Int){
            binding.viewModel = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }
}