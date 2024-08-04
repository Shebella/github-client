package com.bitflyer.github.client.data.service

import com.bitflyer.github.client.data.model.Repo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoRemoteDataSource {

    fun fetchRepositoryList(username: String): Flow<Response<List<Repo>>>
}
