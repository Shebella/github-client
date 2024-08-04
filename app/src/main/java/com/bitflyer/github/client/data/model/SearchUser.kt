package com.bitflyer.github.client.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUser(
    val total_count: Int? = null,
    val incomplete_results: Boolean? = null,
    val items: List<User>? = null
) : Parcelable
