package com.bitflyer.github.client.ui.user.detail

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bitflyer.github.client.databinding.ItemRepoBinding
import com.bitflyer.github.client.ui.dto.RepoDetail

class RepoViewHolder(
    val binding: ItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repoDetail: RepoDetail) {
        binding.repoDetail = repoDetail
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(binding.root.context, Uri.parse(repoDetail.htmlUrl))
        }
    }
}
