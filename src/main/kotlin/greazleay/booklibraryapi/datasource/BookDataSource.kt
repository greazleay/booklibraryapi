package greazleay.booklibraryapi.datasource

import greazleay.booklibraryapi.model.Book
import greazleay.booklibraryapi.dto.AuthorToBookDto
import greazleay.booklibraryapi.dto.GenreToBookDto

interface BookDataSource {

    fun getBooks(): MutableIterable<Book>
    fun getBook(bookId: String): Book
    fun addNewBook(book: Book): Book
    fun updateBook(book: Book): Book
    fun addAuthorToBook(authorToBookDto: AuthorToBookDto): Book
    fun removeAuthorFromBook(authorToBookDto: AuthorToBookDto): Book
    fun addGenreToBook(genreToBookDto: GenreToBookDto): Book
    fun removeGenreFromBook(genreToBookDto: GenreToBookDto): Book
    fun deleteBook(bookId: String)
}