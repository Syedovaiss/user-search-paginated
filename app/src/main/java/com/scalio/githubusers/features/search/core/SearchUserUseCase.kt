package com.scalio.githubusers.features.search.core

import androidx.paging.PagingData
import com.scalio.githubusers.features.search.data.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchUserUseCase {
    suspend fun searchUser(login: String): Flow<PagingData<Item>>
}

class DefaultSearchUserUseCase @Inject constructor(
        private val repository: SearchUserRepository
) : SearchUserUseCase {

    override suspend fun searchUser(login: String): Flow<PagingData<Item>> = repository.getUsersByKeyword(login)


}
