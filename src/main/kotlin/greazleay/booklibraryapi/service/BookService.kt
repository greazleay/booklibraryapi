package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import org.springframework.stereotype.Service


@Service
class BookService(private val dataSource: BookDataSource) {

    fun getBooks(): Collection<Book> = dataSource.getBooks()

    fun getBook(bookId: String): Book = dataSource.getBook(bookId)
}