package com.karma.githubusersearch.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.karma.githubusersearch.R
import com.karma.githubusersearch.databinding.ActivityMainBinding
import com.karma.githubusersearch.di.DatabaseComponent
import com.karma.githubusersearch.di.DatabaseModuleDependencies
import com.karma.githubusersearch.presentation.ui.adapter.SearchAdapter
import com.karma.githubusersearch.presentation.ui.adapter.SearchRepoStateAdapter
import com.karma.githubusersearch.presentation.viewmodels.MainViewModel
import dagger.hilt.android.EntryPointAccessors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDatabaseComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    DatabaseModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        sendSearchQuery()
        getListOfSearch()
        initLoadState()
        initLoadStateWhenEmptyView()
        initLoadStateShowWelcomeMessage()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initAdapter() {
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setOnTouchListener { _, motionEvent ->
            binding.recyclerView.onTouchEvent(motionEvent)
            true
        }

        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = SearchRepoStateAdapter { adapter.retry() },
            footer = SearchRepoStateAdapter { adapter.retry() }
        )
    }

    private fun sendSearchQuery() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.getUserFromApi(it)
                    binding.searchView.clearFocus()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getListOfSearch() {
        viewModel.resultUserApiPaging.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun initLoadState() {
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                mainImage.isVisible = loadState.source.refresh is LoadState.Error
                errorSearchText.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun initLoadStateWhenEmptyView() {
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    errorSearchText.isVisible = true
                    mainImage.isVisible = true
                }
            }
        }
    }

    private fun initLoadStateShowWelcomeMessage() {
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                if (adapter.itemCount > 1 || loadState.source.refresh is LoadState.Loading || loadState.source.refresh !is LoadState.Error) {
                    welcomeSearchText.visibility = View.GONE
                } else {
                    welcomeSearchText.visibility = View.VISIBLE
                    errorSearchText.isVisible = false
                }
            }
        }
    }
}