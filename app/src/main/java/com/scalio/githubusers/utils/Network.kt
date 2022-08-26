package com.scalio.githubusers.utils

import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException
import java.net.UnknownHostException

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error(val viewError: ViewError) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$viewError]"
            Loading -> "Loading"
        }
    }
}

data class ViewError(
        val message: String,
        val code: Int = -1
)

suspend fun <T> execute(call: suspend () -> T): ResponseResult<T> {
    return try {
        val result = call.invoke()
        ResponseResult.Success(result)
    } catch (throwable: Throwable) {
        when (throwable) {
            is EOFException -> getNoResultsFound()
            is HttpException -> getHttpError(throwable)
            is UnknownHostException -> getInternetError()
            is IOException -> ResponseResult.NetworkError
            else -> ResponseResult.Failure(createUnexpectedError(throwable))
        }
    }
}

sealed class ResponseResult<out T> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Failure(val error: ErrorResponse) : ResponseResult<Nothing>()
    object NetworkError : ResponseResult<Nothing>()
}

data class ErrorResponse(
        val errorCode: Int? = 0,
        val path: String?,
        val message: String?
) {
    fun getError() = "$errorCode-$message-$path"
}

fun createUnexpectedError(throwable: Throwable) =
        ErrorResponse(-1, UNEXPECTED_ERROR, throwable.message)

private fun getHttpError(throwable: HttpException): ResponseResult.Failure {
    val errorResponse = convertToErrorResponse(throwable)
    return ResponseResult.Failure(errorResponse)
}

private fun getInternetError(): ResponseResult.Failure {
    val errorResponse = ErrorResponse(
            -1, EMPTY_STRING,
            NO_INTERNET_ERROR
    )
    return ResponseResult.Failure(errorResponse)
}

private fun getNoResultsFound(): ResponseResult.Failure {
    val errorResponse =
            ErrorResponse(-1, EMPTY_STRING, NO_RESULT_FOUND)
    return ResponseResult.Failure(errorResponse)
}

fun convertToErrorResponse(throwable: HttpException): ErrorResponse {
    throwable.response()?.raw()?.let { response ->
        return ErrorResponse(
                response.code,
                response.request.url.toString(),
                SOMETHING_WENT_WRONG
        )
    } ?: return createUnexpectedError(throwable)
}
