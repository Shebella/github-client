package com.bitflyer.github.client.ui.dto

data class RepoDetail (
    val id: Int? = null,
    val name: String? = null,
    val language: String? = null,
    val stargazersCount: Int? = null,
    val description: String? = null,
    val htmlUrl: String? = null
) : Detail()
