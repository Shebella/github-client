package com.bitflyer.github.client.ui.user.detail

import androidx.recyclerview.widget.DiffUtil
import com.bitflyer.github.client.ui.dto.Detail

class UserDetailDiffCallback : DiffUtil.ItemCallback<Detail>() {

    override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
        return oldItem == newItem
    }
}
