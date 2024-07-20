package com.sample.jetpackcomposesample.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.jetpackcomposesample.domain.usecases.LoadRepositoriesUseCase
import com.sample.jetpackcomposesample.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val loadRepositoriesUsecase: LoadRepositoriesUseCase) :
    ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(LoadingState)
    var uiStateLiveData: StateFlow<HomeUiState> = _uiState


    init {
        getRepositories()
    }

    /**======== API call Methods ========*/

    /**======== get Repositories API call Methods ========*/
    private fun getRepositories() = viewModelScope.launch(Dispatchers.IO) {

        loadRepositoriesUsecase().collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    _uiState.value = ContentState(response.data!!)
                }

                is NetworkResult.Error -> {
                    _uiState.value = ErrorState(response.message.orEmpty())
                }

                is NetworkResult.Loading -> {
                    _uiState.value = LoadingState
                }
            }
        }
    }


    fun onEvent(event: HomeUiEvent) {
        when (event) {

            is RetryEvent -> {
                getRepositories()
            }
        }
    }

}

