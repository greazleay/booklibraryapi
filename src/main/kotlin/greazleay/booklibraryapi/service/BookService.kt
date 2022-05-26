package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class BookService(@Qualifier("database") private val dataSource: BookDataSource) {

    fun getBooks(): MutableIterable<Book> = dataSource.getBooks()
    fun getBook(bookId: String): Book = dataSource.getBook(bookId)
    fun addNewBook(book: Book): Book = dataSource.addNewBook(book)
    fun updateBook(book: Book): Book = dataSource.updateBook(book)
    fun deleteBook(bookId: String): Unit = dataSource.deleteBook(bookId)
}