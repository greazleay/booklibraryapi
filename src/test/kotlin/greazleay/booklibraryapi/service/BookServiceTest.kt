package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.BookDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookServiceTest {

    private val dataSource: BookDataSource = mockk(relaxed = true)
    private val bookService = BookService(dataSource)

    @Test
    fun `should call it's datasource to retrieve Books` () {

        // given
        // every { dataSource.getBooks() } returns emptyList()

        // when
        val books = bookService.getBooks()

        // then
        verify(exactly = 1) { dataSource.getBooks() }

    }
}