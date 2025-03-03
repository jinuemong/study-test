package com.studytest.paging.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.studytest.paging.data.PagingUser

class ExamplePagingDataAdapter(
    diffCallback: DiffUtil.ItemCallback<PagingUser>
): PagingDataAdapter<PagingUser,PagingUserViewHolder > (diffCallback){
    override fun onBindViewHolder(holder: PagingUserViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingUserViewHolder {
        return PagingUserViewHolder(parent)
    }
}
