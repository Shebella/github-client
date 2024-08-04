package com.bitflyer.github.client.ui.user.list

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bitflyer.github.client.R
import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.databinding.ItemUserListBinding
import com.bitflyer.github.client.ui.user.detail.UserDetailFragment

class UserListViewHolder(
    private val binding: ItemUserListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.user = user
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            val bundle = bundleOf(UserDetailFragment.USERNAME to user.login)
            Navigation.findNavController(binding.root).navigate(
                R.id.action_user_list_to_user_detail,
                bundle
            )
        }
    }
}
