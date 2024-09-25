package com.karma.githubusersearch.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karma.githubusersearch.R
import com.karma.githubusersearch.databinding.ItemUserBinding
import com.karma.githubusersearch.presentation.domain.model.User


class SearchAdapter :
    PagingDataAdapter<User, SearchAdapter.SearchViewHolder>(REPO_SEARCH_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = getItem(position)
        if (search != null) {
            holder.itemRepoBinding.repoModel = search
        }
    }

    inner class SearchViewHolder(var itemRepoBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(
            itemRepoBinding.root
        )

    companion object {
        private val REPO_SEARCH_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.username == newItem.username

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem
        }
    }
}