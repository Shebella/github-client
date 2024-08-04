package com.bitflyer.github.client.data.model

import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.dto.UserDetail

val userListMock
    get() = listOf(
        User(
            login = "bitFlyer",
            id = 34338629,
            avatar_url = "https://avatars.githubusercontent.com/u/34338629?v=4",
            name = null,
            followers = 0,
            following = 0
        ),
        User(
            login = "bitFlyer-Blockchain",
            id = 69892253,
            avatar_url = "https://avatars.githubusercontent.com/u/69892253?v=4",
            name = "bitFlyer Blockchain",
            followers = 1,
            following = 0
        )
    )

val userDetailMock
    get() = UserDetail(
        id = 69892253,
        iconUrl = "https://avatars.githubusercontent.com/u/69892253?v=4",
        userName = "bitFlyer-Blockchain",
        fullName = "bitFlyer Blockchain",
        followers = 1,
        following = 0
    )

val repoListMock
    get() = listOf(
        RepoDetail(
            id = 291386873,
            name = "bls",
            language = "C#",
            stargazersCount = 0,
            description = null,
            htmlUrl = "https://github.com/bitFlyer-Blockchain/bls"
        ),
        RepoDetail(
            id = 290393878,
            name = "cookiePoint",
            language = "C#",
            stargazersCount = 0,
            description = "Sample demo project for creating a cryptocurrency wallet in miyabi",
            htmlUrl = "https://github.com/bitFlyer-Blockchain/cookiePoint"
        )
    )

val rateLimitMock
    get() = "API rate limit exceeded. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)"

val noNetworkMock
    get() = "Unable to resolve host \"api.github.com\": No address associated with hostname."
