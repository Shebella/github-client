package com.bitflyer.github.client.domain.usecase

import com.bitflyer.github.client.data.repository.RepoRepository
import com.bitflyer.github.client.data.repository.UserRepository
import com.bitflyer.github.client.ui.dto.Detail
import com.bitflyer.github.client.ui.state.ApiResult
import com.bitflyer.github.client.ui.state.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val repoRepository: RepoRepository
) {
    operator fun invoke(username: String): Flow<ApiResult<List<Detail>>> {
        return combine(
            userRepository.getUserDetail(username),
            repoRepository.getRepositoryList(username)
        ) { userDetail, repoDetailList ->
            if (userDetail.status == ApiStatus.SUCCESS && repoDetailList.status == ApiStatus.SUCCESS) {
                val detailList = mutableListOf<Detail>()

                userDetail.data?.let { user ->
                    detailList.add(user)
                }

                repoDetailList.data?.forEach { repoDetail ->
                    detailList.add(repoDetail)
                }

                ApiResult.success(detailList)
            } else {
                ApiResult.error(userDetail.message.toString())
            }
        }
    }
}
