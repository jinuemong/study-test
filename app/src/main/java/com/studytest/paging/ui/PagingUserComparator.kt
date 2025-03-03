package com.studytest.paging.ui

import androidx.recyclerview.widget.DiffUtil
import com.studytest.paging.data.PagingUser

object PagingUserComparator:  DiffUtil.ItemCallback<PagingUser>() {
    override fun areItemsTheSame(oldItem: PagingUser, newItem: PagingUser): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PagingUser, newItem: PagingUser): Boolean {
        return oldItem == newItem
    }
}
