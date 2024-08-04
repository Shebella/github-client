package com.bitflyer.github.client.domain.util

import com.bitflyer.github.client.data.model.Repo
import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.dto.UserDetail

fun User.toUserDetail(): UserDetail {
    return UserDetail(
        id = id,
        iconUrl = avatar_url,
        userName = login,
        fullName = name,
        followers = followers,
        following = following
    )
}

fun Repo.toRepoDetail(): RepoDetail {
    return RepoDetail(
        id = id,
        name = name,
        language = language,
        stargazersCount = stargazers_count,
        description = description,
        htmlUrl = html_url
    )
}
