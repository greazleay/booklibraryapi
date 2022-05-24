package greazleay.booklibraryapi.datasource.mock

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBookDataSourceTest {

    private val mockDataSource = MockBookDataSource()

    @Test
    fun `should provide a list of books` () {

        // when
        val books = mockDataSource.getBooks()

        // then
        assertThat(books.size).isGreaterThanOrEqualTo(2)
    }

    @Test
    fun `should provide some mock data` () {
        // when
        val books = mockDataSource.getBooks()
        // then
        assertThat(books).allMatch { it.author.isNotEmpty() }
        assertThat(books).allMatch { it.numOfPages > 0 }
        assertThat(books).anyMatch { !it.isRead }
    }
}