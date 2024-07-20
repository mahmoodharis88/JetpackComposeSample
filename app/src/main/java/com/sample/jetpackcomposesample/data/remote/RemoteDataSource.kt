package com.sample.jetpackcomposesample.data.remote

import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.data.remote.network.NetworkApi
import retrofit2.Response


import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getRepositories(): Response<RepositoriesResponse> {
        return networkApi.loadRepositories()
    }

}