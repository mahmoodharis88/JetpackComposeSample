package com.sample.jetpackcomposesample.data.di

import com.sample.jetpackcomposesample.data.local.LocalDataSource
import com.sample.jetpackcomposesample.data.remote.RemoteDataSource
import com.sample.jetpackcomposesample.data.repository.Repository
import com.sample.jetpackcomposesample.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource, localDataSource)
    }
}
