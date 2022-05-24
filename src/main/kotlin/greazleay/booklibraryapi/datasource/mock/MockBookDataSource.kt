package greazleay.booklibraryapi.datasource.mock

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import org.springframework.stereotype.Repository

@Repository
class MockBookDataSource : BookDataSource {

    val books = listOf(
        Book(id = "1", "Alice in Wonderland", "J.T Tolkien", 500, false),
        Book(id = "2", title = "A Song of Fire and Ice", author = "R.R Martin", numOfPages = 1000, isRead = true),
        Book(id = "3", title = "Harry Porter", author = "J. Abrams", numOfPages = 700, isRead = true)
    )

    override fun getBooks(): Collection<Book> = books

    override fun getBook(bookId: String): Book =
        books.firstOrNull() { it.id == bookId }
            ?: throw NoSuchElementException("Could not find a book with id $bookId")

}