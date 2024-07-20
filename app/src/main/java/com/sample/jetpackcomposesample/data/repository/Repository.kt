package com.sample.jetpackcomposesample.data.repository

import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadRepositories():  Flow<NetworkResult<RepositoriesResponse>>
}
