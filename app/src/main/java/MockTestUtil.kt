

import com.sample.jetpackcomposesample.data.model.RepositoriesResponse

class MockTestUtil {
    companion object {
        fun createRepositories(count: Int): List<RepositoriesResponse.Item> {
            return (0 until count).map {
                RepositoriesResponse.Item(
                    id = it,
                    name = "go",
                    fullName = "golang/go",
                    description = "The Go programming language",
                    forksCount = 17260,
                    language = "Go"
                )
            }
        }
    }
}
