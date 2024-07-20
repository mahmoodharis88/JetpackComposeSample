package com.sample.jetpackcomposesample.data.repository

import com.sample.jetpackcomposesample.data.local.LocalDataSource
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.data.remote.RemoteDataSource
import com.sample.jetpackcomposesample.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : Repository {

    val remote = remoteDataSource
    val local = localDataSource

    override suspend fun loadRepositories(): Flow<NetworkResult<RepositoriesResponse>> {
        return flow {
            try {
                remote.getRepositories().apply {
                    if (this.isSuccessful) {
                        local.insertRepositories(this.body()!!)
                    }
                    emit(NetworkResult.Success(data = local.readRepositories()))

                }

            } catch (e: Exception) {
                if (local.readRepositories() == null) {
                    emit(NetworkResult.Error(message = e.message.toString(), data = null))
                } else {
                    emit(NetworkResult.Success(data = local.readRepositories()))
                }
            }

        }
    }

}