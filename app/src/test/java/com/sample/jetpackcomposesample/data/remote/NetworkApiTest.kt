package com.sample.jetpackcomposesample.data.remote
import com.sample.jetpackcomposesample.MainCoroutineRule
import com.sample.jetpackcomposesample.data.remote.api.ApiAbstract
import com.sample.jetpackcomposesample.data.remote.network.NetworkApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class NetworkApiTest : ApiAbstract<NetworkApi>() {

    private lateinit var apiService: NetworkApi

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        apiService = createService(NetworkApi::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test loadRepositories returns list of Repositories`() = runBlocking {
        // Given
        enqueueResponse("/repositories_response.json")

        // Invoke
        val response = apiService.loadRepositories()
        val responseBody = response.body()?.items!!
        mockWebServer.takeRequest()

        // Then
        assertThat(responseBody[0].name, `is`("go"))
        assertThat(responseBody[0].fullName, `is`("golang/go"))
        assertThat(responseBody[0].description, `is`("The Go programming language"))
        assertThat(responseBody[0].language, `is`("Go"))
    }

}
