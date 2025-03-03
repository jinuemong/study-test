package com.studytest.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState


class ExamplePagingSource(
    val service: ExamplePagingService,
    val query: String,
) : PagingSource<Int, PagingUser>() {
    // page 키를 찾는 함수
    override fun getRefreshKey(
        state: PagingState<Int, PagingUser>
    ): Int? {
        val position = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(position)
        val prevKey = anchorPage?.prevKey
        val nextKey = anchorPage?.nextKey
        // prevKey가 null이 아니라면 prevKey + 1 반환
        // prevKey가 null이고, nextKey가 null이 아니라면 nextKey - 1 반환
        // 모두 null이면 null 반환
        // 이 경우 anchorPage는 page를 초기화합니다.
        return prevKey?.plus(1) ?: nextKey?.minus(1)
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PagingUser> {
        return try {
            // key가 null이라면 1페이지부터 로드
            val nextPageNumber = params.key ?: 1
            val users = service.searchUser(query, nextPageNumber)
            val prevPage = if (nextPageNumber > 1) nextPageNumber - 1 else null
            val nextKey = if (users.isEmpty()) null else nextPageNumber + 1

            LoadResult.Page(
                data = users,
                prevKey = prevPage,
                nextKey =nextKey
            )
        } catch (e: Exception){
            // error handle
            LoadResult.Error(e)
        }
    }
}
