package com.sample.jetpackcomposesample.data.remote.network

import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET(Constants.REPOSITORIES_API)
    suspend fun loadRepositories(): Response<RepositoriesResponse>

}