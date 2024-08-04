package com.bitflyer.github.client.domain.usecase

import com.bitflyer.github.client.BaseUnitTest
import com.bitflyer.github.client.data.model.noNetworkMock
import com.bitflyer.github.client.data.model.repoListMock
import com.bitflyer.github.client.data.repository.RepoRepository
import com.bitflyer.github.client.data.repository.UserRepository
import com.bitflyer.github.client.data.model.userDetailMock
import com.bitflyer.github.client.ui.dto.Detail
import com.bitflyer.github.client.ui.dto.RepoDetail
import com.bitflyer.github.client.ui.dto.UserDetail
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

class GetUserDetailUseCaseTest : BaseUnitTest() {

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var repoRepository: RepoRepository

    @Before
    override fun setup() {
        super.setup()
        getUserDetailUseCase = GetUserDetailUseCase(userRepository, repoRepository)
    }

    @Test
    fun test_get_user_detail_user_case_success() {
        runTest {
            val successUserDetail = ApiResult.success(userDetailMock)

            coEvery {
                userRepository.getUserDetail(any())
            }.returns(
                flowOf(successUserDetail)
            )

            val successRepoDetailList = ApiResult.success(repoListMock)

            coEvery {
                repoRepository.getRepositoryList(any())
            }.returns(
                flowOf(successRepoDetailList)
            )

            val detailList = mutableListOf<Detail>()

            successUserDetail.data?.let { user ->
                detailList.add(user)
            }

            successRepoDetailList.data?.forEach { repoDetail ->
                detailList.add(repoDetail)
            }

            val apiResult = getUserDetailUseCase.invoke("bitflyer").first()
            assertThat(apiResult.status, `is`(ApiStatus.SUCCESS))
            assertThat(apiResult.data, `is`(detailList))
            assertThat(apiResult.message, nullValue())
        }
    }

    @Test
    fun test_get_user_detail_user_case_error() {
        runTest {
            val userDetailNoNetwork = ApiResult.error<UserDetail>(noNetworkMock)

            coEvery {
                userRepository.getUserDetail(any())
            }.returns(
                flowOf(userDetailNoNetwork)
            )

            val repoDetailNoNetwork = ApiResult.error<List<RepoDetail>>(noNetworkMock)

            coEvery {
                repoRepository.getRepositoryList(any())
            }.returns(
                flowOf(repoDetailNoNetwork)
            )

            val apiResult = getUserDetailUseCase.invoke("bitflyer").first()
            assertThat(apiResult.status, `is`(ApiStatus.ERROR))
            assertThat(apiResult.data, nullValue())
            assertThat(apiResult.message, `is`(noNetworkMock))
        }
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}
