package com.bitflyer.github.client.domain.usecase

import com.bitflyer.github.client.BaseUnitTest
import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.data.model.rateLimitMock
import com.bitflyer.github.client.data.repository.UserRepository
import com.bitflyer.github.client.data.model.userListMock
import com.bitflyer.github.client.ui.state.ApiResult
import com.bitflyer.github.client.ui.state.ApiStatus
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetUserListUseCaseTest : BaseUnitTest() {

    private lateinit var getUserListUseCase: GetUserListUseCase

    @MockK
    lateinit var userRepository: UserRepository

    @Before
    override fun setup() {
        super.setup()
        getUserListUseCase = GetUserListUseCase(userRepository)
    }

    @Test
    fun test_get_user_list_use_case_success() {
        runTest {
            val successUserList = ApiResult.success(userListMock)

            coEvery {
                userRepository.getUserList(any())
            }.returns(
                flowOf(successUserList)
            )

            val apiResult = getUserListUseCase.invoke("bitflyer").first()
            assertThat(apiResult.status, `is`(ApiStatus.SUCCESS))
            assertThat(apiResult.data, `is`(userListMock))
            assertThat(apiResult.message, nullValue())
        }
    }

    @Test
    fun test_get_user_list_use_case_error() {
        runTest {
            val errorRateLimit = ApiResult.error<List<User>>(rateLimitMock)

            coEvery {
                userRepository.getUserList(any())
            }.returns(
                flowOf(errorRateLimit)
            )

            val apiResult = getUserListUseCase.invoke("bitflyer").first()
            assertThat(apiResult.status, `is`(ApiStatus.ERROR))
            assertThat(apiResult.data, nullValue())
            assertThat(apiResult.message, `is`(rateLimitMock))
        }
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}
