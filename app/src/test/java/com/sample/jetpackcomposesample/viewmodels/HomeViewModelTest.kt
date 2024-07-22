package com.sample.jetpackcomposesample.viewmodels


import MockTestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.jetpackcomposesample.data.local.LocalDataSource
import com.sample.jetpackcomposesample.data.local.database.RepositoriesDao
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.data.remote.RemoteDataSource
import com.sample.jetpackcomposesample.data.repository.RepositoryImpl
import com.sample.jetpackcomposesample.domain.usecases.LoadRepositoriesUseCase
import com.sample.jetpackcomposesample.presentation.home.ContentState
import com.sample.jetpackcomposesample.presentation.home.ErrorState
import com.sample.jetpackcomposesample.presentation.home.HomeViewModel
import com.sample.jetpackcomposesample.presentation.home.LoadingState
import com.sample.jetpackcomposesample.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var repository: RepositoryImpl
    private lateinit var remote: RemoteDataSource
    private lateinit var local: LocalDataSource
    private lateinit var viewModel: HomeViewModel
    private lateinit var repositoriesDao: RepositoriesDao

    @MockK
    private lateinit var loadRepositoriesUsecase: LoadRepositoriesUseCase


    @Before
    public override fun setUp() {

        MockKAnnotations.init(this)

        Dispatchers.setMain(testDispatcher)

        remote = mock(RemoteDataSource::class.java)
        local = mock(LocalDataSource::class.java)

        repositoriesDao = mock(RepositoriesDao::class.java)

        repository = RepositoryImpl(remoteDataSource = remote, localDataSource = local)

    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
    }


    @Test
    fun getRepositoriesSuccessCase() = testDispatcher.runBlockingTest {
        val givenRepositories = MockTestUtil.createRepositories(3)


        //when
        coEvery { loadRepositoriesUsecase.invoke() }.returns(
            flowOf(
                NetworkResult.Success(
                    RepositoriesResponse(incompleteResults = false, items = givenRepositories)
                )
            )
        )

        viewModel = HomeViewModel(loadRepositoriesUsecase)
        coVerify(exactly = 1) { loadRepositoriesUsecase.invoke() }

        when (val state = viewModel.uiStateLiveData.value) {
            is ContentState -> {
                val response = state.response.items
                assertThat(response[0].name, `is`("go"))
                assertThat(response[0].fullName, `is`("golang/go"))
                assertThat(response[0].description, `is`("The Go programming language"))
                assertThat(response[0].language, `is`("Go"))
            }

            is ErrorState -> {
            }

            LoadingState -> {}
        }


    }
}