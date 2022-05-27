package greazleay.booklibraryapi.datasource

import greazleay.booklibraryapi.model.Book

interface BookDataSource {

    fun getBooks(): MutableIterable<Book>
    fun getBook(bookId: String): Book
    fun addNewBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun deleteBook(bookId: String): Unit
}