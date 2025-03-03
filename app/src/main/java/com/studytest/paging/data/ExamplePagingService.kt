package com.studytest.paging.data

class ExamplePagingService{
    fun searchUser(
        query: String,
        nextPageNumber: Int,
    ): List<PagingUser> {
        return listOf(
            PagingUser(1,"1"),
            PagingUser(2,"2"),
        )
    }
}
