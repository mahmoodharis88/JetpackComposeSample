package com.sample.jetpackcomposesample.presentation.home


sealed class HomeUiEvent

data object RetryEvent : HomeUiEvent()
