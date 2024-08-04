package com.bitflyer.github.client.ui.user.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bitflyer.github.client.R
import com.bitflyer.github.client.databinding.ItemRepoBinding
import com.bitflyer.github.client.databinding.ItemUserBinding
import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.dto.UserDetail
import com.bitflyer.github.client.ui.dto.Detail

class UserDetailAdapter : ListAdapter<Detail, ViewHolder>(UserDetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_user -> {
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                UserViewHolder(binding)
            }
            else -> {
                val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
                RepoViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val detail = getItem(position)) {
            is UserDetail -> (holder as UserViewHolder).bind(detail)
            is RepoDetail -> (holder as RepoViewHolder).bind(detail)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UserDetail -> R.layout.item_user
            is RepoDetail -> R.layout.item_repo
        }
    }
}
