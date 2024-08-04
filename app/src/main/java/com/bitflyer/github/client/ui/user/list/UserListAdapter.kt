package com.bitflyer.github.client.ui.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.databinding.ItemUserListBinding

class UserListAdapter : ListAdapter<User, UserListViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
