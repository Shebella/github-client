package com.bitflyer.github.client.ui.state

data class ApiResult<out T>(
    val status: ApiStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> loading(): ApiResult<T> {
            return ApiResult(ApiStatus.LOADING, null, null)
        }

        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(ApiStatus.SUCCESS, data, null)
        }

        fun <T> error(message: String): ApiResult<T> {
            return ApiResult(ApiStatus.ERROR, null, message)
        }
    }
}
