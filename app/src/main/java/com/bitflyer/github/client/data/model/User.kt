package com.bitflyer.github.client.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String? = null,
    val id: Int? = null,
    val avatar_url: String? = null,
    val name: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
) : Parcelable
