package com.bitflyer.github.client.data.repository

import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.ui.dto.UserDetail
import com.bitflyer.github.client.ui.state.ApiResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserList(query: String): Flow<ApiResult<List<User>>>

    fun getUserDetail(username: String): Flow<ApiResult<UserDetail>>
}
