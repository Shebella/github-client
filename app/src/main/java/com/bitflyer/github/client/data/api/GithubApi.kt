package com.bitflyer.github.client.data.api

import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.data.model.SearchUser
import com.bitflyer.github.client.data.model.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<SearchUser>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<User>

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): Response<List<Repo>>
}
