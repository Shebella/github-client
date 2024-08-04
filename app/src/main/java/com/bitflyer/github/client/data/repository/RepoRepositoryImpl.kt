package com.bitflyer.github.client.data.repository

import com.bitflyer.github.client.data.service.RepoRemoteDataSource
import com.bitflyer.github.client.domain.util.toRepoDetail
import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.state.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoRemoteDataSource: RepoRemoteDataSource
) : RepoRepository {

    override fun getRepositoryList(username: String): Flow<ApiResult<List<RepoDetail>>> {
        return repoRemoteDataSource.fetchRepositoryList(username)
            .map { response ->
                if (response.isSuccessful) {
                    ApiResult.success(
                        response.body()?.let { repoList ->
                            repoList.filter { repo ->
                                repo.fork == false
                            }.map { repo ->
                                repo.toRepoDetail()
                            }
                        }
                    )
                } else {
                    ApiResult.error(response.code().toString())
                }
            }
    }
}
