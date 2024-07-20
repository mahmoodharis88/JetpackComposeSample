package com.sample.jetpackcomposesample.presentation.home

import com.sample.jetpackcomposesample.data.model.RepositoriesResponse

sealed class HomeUiState

data object LoadingState : HomeUiState()
class ContentState(val response: RepositoriesResponse) : HomeUiState()
class ErrorState(val message: String) : HomeUiState()