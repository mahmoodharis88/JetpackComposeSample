package com.sample.jetpackcomposesample.domain.usecases

import MockTestUtil
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.data.repository.Repository
import com.sample.jetpackcomposesample.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoadRepositoriesUsecaseTest {

    @MockK
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking loadRepositoriesUsecase gives list of Repositories`() = runBlocking {
        // Given
        val useCase = LoadRepositoriesUseCase(repository)
        val givenRepositories = MockTestUtil.createRepositories(3)

        // When
        coEvery { repository.loadRepositories() }
            .returns(flowOf(NetworkResult.Success(RepositoriesResponse(false, givenRepositories))))

        // Invoke
        val repositoriesListFlow = useCase()

        // Then
        MatcherAssert.assertThat(repositoriesListFlow, CoreMatchers.notNullValue())

        val repositoriesListDataState = repositoriesListFlow.first()
        MatcherAssert.assertThat(repositoriesListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            repositoriesListDataState,
            CoreMatchers.instanceOf(NetworkResult.Success::class.java)
        )

        val repositoriesList = (repositoriesListDataState as NetworkResult.Success).data
        MatcherAssert.assertThat(repositoriesList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(repositoriesList?.items?.size, `is`(givenRepositories.size))
    }
}
