package com.scalio.githubusers.features.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.scalio.githubusers.base.BaseFragment
import com.scalio.githubusers.databinding.FragmentSearchUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : BaseFragment<FragmentSearchUserBinding>() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun getBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentSearchUserBinding.inflate(inflater, container, false).apply {
        viewModel = searchViewModel
        adapter = UserSearchAdapter { item ->
            searchViewModel.onItemClick(item)
        }
    }

    override fun onFragmentReady() {
        searchViewModel.onInit()
    }


}