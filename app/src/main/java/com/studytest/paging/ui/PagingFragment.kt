package com.studytest.paging.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.studytest.R
import com.studytest.paging.data.ExamplePagingService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingFragment : Fragment() {
    private val pagingService = ExamplePagingService()
    private val viewModel = ExamplePagingViewModel(pagingService)
    private val pagingDataAdapter = ExamplePagingDataAdapter(PagingUserComparator)
    //recyclerView.adapter = pagingDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingDataAdapter.submitData(pagingData)
            }
        }
    }
}
