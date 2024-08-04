package com.bitflyer.github.client.data.repository

import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.data.service.UserRemoteDataSource
import com.bitflyer.github.client.domain.util.toUserDetail
import com.bitflyer.github.client.ui.dto.UserDetail
import com.bitflyer.github.client.ui.state.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun getUserList(query: String): Flow<ApiResult<List<User>>> {
        return userRemoteDataSource.fetchUserList(query)
            .map { response ->
                if (response.isSuccessful) {
                    ApiResult.success(response.body()?.items)
                } else {
                    ApiResult.error(response.code().toString())
                }
            }
    }

    override fun getUserDetail(username: String): Flow<ApiResult<UserDetail>> {
        return userRemoteDataSource.fetchUserDetail(username)
            .map { response ->
                if (response.isSuccessful) {
                    ApiResult.success(response.body()?.toUserDetail())
                } else {
                    ApiResult.error(response.code().toString())
                }
            }
    }
}
