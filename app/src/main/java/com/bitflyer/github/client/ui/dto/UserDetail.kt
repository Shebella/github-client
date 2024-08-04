package com.bitflyer.github.client.ui.dto

data class UserDetail (
    val id: Int? = null,
    val iconUrl: String? = null,
    val userName: String? = null,
    val fullName: String? = null,
    val followers: Int? = null,
    val following: Int? = null
) : Detail()
