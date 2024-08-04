package com.bitflyer.github.client.data.service

import com.bitflyer.github.client.data.api.GithubApi
import com.bitflyer.github.client.data.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class RepoRemoteDataSourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : RepoRemoteDataSource {

    override fun fetchRepositoryList(username: String): Flow<Response<List<Repo>>> {
        return flow {
            emit(githubApi.getUserRepositories(username))
        }
    }
}
