package com.bitflyer.github.client.data.repository

import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.state.ApiResult
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getRepositoryList(username: String): Flow<ApiResult<List<RepoDetail>>>
}
