package com.bitflyer.github.client.ui.user.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflyer.github.client.domain.usecase.GetUserDetailUseCase
import com.bitflyer.github.client.ui.dto.Detail
import com.bitflyer.github.client.ui.state.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _detailList = MutableLiveData<ApiResult<List<Detail>>>()

    val detailList: MutableLiveData<ApiResult<List<Detail>>> = _detailList

    fun getUserDetail(username: String) {
        _detailList.value = ApiResult.loading()

        viewModelScope.launch {
            userDetailUseCase(username)
                .catch { throwable ->
                    _detailList.value = ApiResult.error(throwable.message.toString())
                }
                .collect { detailList ->
                    _detailList.value = detailList
                }
        }
    }
}
