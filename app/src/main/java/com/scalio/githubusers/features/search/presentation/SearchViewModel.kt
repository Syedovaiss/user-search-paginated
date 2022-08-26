package com.scalio.githubusers.features.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.scalio.githubusers.R
import com.scalio.githubusers.features.search.core.SearchUserUseCase
import com.scalio.githubusers.features.search.data.Item
import com.scalio.githubusers.features.search.data.PagingState
import com.scalio.githubusers.manager.StringResourceManager
import com.scalio.githubusers.manager.ToastManager
import com.scalio.githubusers.utils.PagingStateListener
import com.scalio.githubusers.utils.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
        private val searchUserUseCase: SearchUserUseCase,
        private val toastManager: ToastManager,
        private val stringResourceManager: StringResourceManager
) : ViewModel() {

    private val _scope by lazy { MutableLiveData<CoroutineScope>() }
    val scope: LiveData<CoroutineScope> get() = _scope

    private val _progressState by lazy { MutableLiveData<Boolean>() }
    val progressState: LiveData<Boolean> get() = _progressState

    private val _pagedData by lazy { MutableLiveData<PagingData<Item>>() }
    val pagedData: LiveData<PagingData<Item>> get() = _pagedData

    val pagingState by lazy {
        object : PagingStateListener {
            override fun onState(state: PagingState, message: Any?) {
                when (state) {
                    PagingState.LOADING -> {
                        _progressState.value = true
                    }
                    PagingState.ERROR -> {
                        toastManager.showToast(message.toString())
                        _progressState.value = false
                    }
                    else -> {
                        _progressState.value = false
                    }
                }
            }
        }
    }

    fun onInit() {
        _scope.value = viewModelScope
    }

    fun searchUser(searchedQuery: String) {
        if (searchedQuery.isNotEmpty()) {
            getUsers(searchedQuery)
        } else {
            toastManager.showToast(stringResourceManager.getString(R.string.empty_searched_text))
        }
    }

    private fun getUsers(searchedQuery: String) {
        viewModelScope.launch {
            searchUserUseCase.searchUser(searchedQuery).cachedIn(viewModelScope).collectLatest {
                _pagedData.value = it
            }
        }
    }

    fun onItemClick(item: Item) {
        toastManager.showToast(stringResourceManager.getString(R.string.item_click, item.login.default()))
    }


}