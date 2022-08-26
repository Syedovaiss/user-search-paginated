package com.scalio.githubusers.features.search.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scalio.githubusers.features.search.data.Item
import com.scalio.githubusers.utils.DEFAULT_PAGE
import com.scalio.githubusers.utils.default
import com.scalio.githubusers.utils.minus
import timber.log.Timber
import javax.inject.Inject


class SearchUserPagedDataSource @Inject constructor(private val service: SearchAPIService) :
        PagingSource<Int, Item>() {

    private var _login: String? = null

    fun setLogin(login: String) {
        this._login = login
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> = try {
        val position = params.key ?: DEFAULT_PAGE
        val response = service.getUsersByKeyWord(_login.default(), position)
        LoadResult.Page(
                response.items.orEmpty(),
                if (position == 1) null else position - 1,
                position + 1
        )
    } catch (e: Exception) {
        Timber.e(e)
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? =
            state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: (anchorPage?.nextKey minus 1)
            }
}