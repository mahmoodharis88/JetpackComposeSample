package com.sample.jetpackcomposesample.data.di

import android.content.Context
import androidx.room.Room
import com.sample.jetpackcomposesample.util.Constants.DATABASE_NAME
import com.sample.jetpackcomposesample.data.local.database.RepositoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RepositoriesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: RepositoriesDatabase) = database.repositoriesDao()
}