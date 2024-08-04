package com.bitflyer.github.client.domain.usecase

import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.data.repository.UserRepository
import com.bitflyer.github.client.ui.state.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(query: String): Flow<ApiResult<List<User>>> {
        return userRepository.getUserList(query)
    }
}
