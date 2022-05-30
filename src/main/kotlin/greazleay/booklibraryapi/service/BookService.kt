package greazleay.booklibraryapi.service

import greazleay.booklibraryapi.datasource.BookDataSource
import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.dto.AuthorToBookDto
import greazleay.booklibraryapi.dto.GenreToBookDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class BookService(@Qualifier("book-db") private val dataSource: BookDataSource) {

    fun getBooks(): MutableIterable<Book> = dataSource.getBooks()
    fun getBook(bookId: String): Book = dataSource.getBook(bookId)
    fun addNewBook(book: Book): Book = dataSource.addNewBook(book)
    fun updateBook(book: Book): Book = dataSource.updateBook(book)
    fun addAuthorToBook(authorToBookDto: AuthorToBookDto): Book = dataSource.addAuthorToBook(authorToBookDto)
    fun removeAuthorFromBook(authorToBookDto: AuthorToBookDto): Book = dataSource.removeAuthorFromBook(authorToBookDto)
    fun addGenreToBook(genreToBookDto: GenreToBookDto): Book = dataSource.addGenreToBook(genreToBookDto)
    fun removeGenreFromBook(genreToBookDto: GenreToBookDto): Book = dataSource.removeGenreFromBook(genreToBookDto)
    fun deleteBook(bookId: String): Unit = dataSource.deleteBook(bookId)
}