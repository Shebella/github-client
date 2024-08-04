package com.bitflyer.github.client.data.service

import com.bitflyer.github.client.data.api.GithubApi
import com.bitflyer.github.client.data.model.SearchUser
import com.bitflyer.github.client.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : UserRemoteDataSource {

    override fun fetchUserList(query: String): Flow<Response<SearchUser>> {
        return flow {
            emit(githubApi.searchUsers(query))
        }
    }

    override fun fetchUserDetail(username: String): Flow<Response<User>> {
        return flow {
            emit(githubApi.getUser(username))
        }
    }
}
