package com.bitflyer.github.client.ui.user.detail

import androidx.recyclerview.widget.RecyclerView
import com.bitflyer.github.client.databinding.ItemUserBinding
import com.bitflyer.github.client.ui.dto.UserDetail

class UserViewHolder(
    val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userDetail: UserDetail) {
        binding.userDetail = userDetail
        binding.executePendingBindings()
    }
}
