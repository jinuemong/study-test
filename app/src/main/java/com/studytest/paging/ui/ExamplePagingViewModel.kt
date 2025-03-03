package com.studytest.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.studytest.paging.data.ExamplePagingService
import com.studytest.paging.data.ExamplePagingSource

class ExamplePagingViewModel(
    service: ExamplePagingService,
) : ViewModel() {
    private var query = ""
    val flow = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
    ) {
        ExamplePagingSource(
            service = service,
            query = query,
        )
    }.flow
        .cachedIn(viewModelScope)
}
