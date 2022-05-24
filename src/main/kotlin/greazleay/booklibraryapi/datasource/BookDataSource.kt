package greazleay.booklibraryapi.datasource

import greazleay.booklibraryapi.model.Book

interface BookDataSource {

    fun getBooks(): Collection<Book>
    fun getBook(bookId: String): Book
    fun addNewBook(book: Book): Book
}