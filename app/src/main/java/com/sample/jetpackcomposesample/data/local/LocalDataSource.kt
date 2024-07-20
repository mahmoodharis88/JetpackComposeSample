package com.sample.jetpackcomposesample.data.local

import com.sample.jetpackcomposesample.data.local.database.RepositoriesDao
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val repositoriesDao: RepositoriesDao
) {

    fun readRepositories(): RepositoriesResponse {
        return repositoriesDao.readRepositories()
    }

    suspend fun insertRepositories(repositoriesResponse: RepositoriesResponse) {
        repositoriesDao.insertRepositories(repositoriesResponse)
    }
}
