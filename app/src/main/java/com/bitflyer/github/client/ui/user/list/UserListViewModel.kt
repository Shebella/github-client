package com.bitflyer.github.client.ui.user.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflyer.github.client.data.model.User
import com.bitflyer.github.client.domain.usecase.GetUserListUseCase
import com.bitflyer.github.client.ui.state.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _userList = MutableLiveData<ApiResult<List<User>>>()

    val userList: MutableLiveData<ApiResult<List<User>>> = _userList

    fun getUserList(query: String) {
        _userList.value = ApiResult.loading()

        viewModelScope.launch {
            getUserListUseCase(query)
                .catch { throwable ->
                    _userList.value = ApiResult.error(throwable.message.toString())
                }.collect { userList ->
                    _userList.value = userList
                }
        }
    }
}
