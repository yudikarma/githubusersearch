package com.karma.githubusersearch.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.usecase.GithubUseCase
import com.karma.githubusersearch.utils.LoaderState
import com.karma.githubusersearch.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCase: GithubUseCase
) : ViewModel() {


    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _error = MutableLiveData<String>()

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean>
        get() = _networkError

    private val _resultUserApi = MutableLiveData<List<User>>()
    val resultUserApi: LiveData<List<User>>
        get() = _resultUserApi

    private val _resultUserApiPaging = MutableLiveData<PagingData<User>>()
    val resultUserApiPaging: LiveData<PagingData<User>>
        get() = _resultUserApiPaging

    fun getUserFromApi(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserFromApi(query).collect {
                when (it) {
                    is ResultState.Success -> {
                        _resultUserApi.postValue(it.data!!)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error!!)
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }

    fun getUserFromApiPaging(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserFromApiPaging(query).collect {
                when (it) {
                    is ResultState.Success -> {
                        _resultUserApiPaging.postValue(it.data!!)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error!!)
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }

}