package com.sample.jetpackcomposesample.data.di

import com.sample.jetpackcomposesample.data.manager.LocalUserMangerImpl
import com.sample.jetpackcomposesample.domain.manger.LocalUserManger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MangerModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManger(localUserMangerImpl: LocalUserMangerImpl) : LocalUserManger
}