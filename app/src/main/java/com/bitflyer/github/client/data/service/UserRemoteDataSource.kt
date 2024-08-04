package com.bitflyer.github.client.data.service

import com.bitflyer.github.client.data.model.SearchUser
import com.bitflyer.github.client.data.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRemoteDataSource {

    fun fetchUserList(query: String): Flow<Response<SearchUser>>

    fun fetchUserDetail(username: String): Flow<Response<User>>
}
