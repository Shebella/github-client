package com.bitflyer.github.client.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val id: Int? = null,
    val name: String? = null,
    val html_url: String? = null,
    val description: String? = null,
    val fork: Boolean? = null,
    val language: String? = null,
    val stargazers_count: Int? = null
) : Parcelable
