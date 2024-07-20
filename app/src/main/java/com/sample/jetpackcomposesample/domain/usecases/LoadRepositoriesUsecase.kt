package com.sample.jetpackcomposesample.domain.usecases

import com.sample.jetpackcomposesample.data.repository.Repository
import javax.inject.Inject

class LoadRepositoriesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadRepositories()
}